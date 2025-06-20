package com.sdgs.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.sdgs.config.OssConfig;
import com.sdgs.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 阿里云OSS文件服务实现类
 */
@Slf4j
@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private OssConfig ossConfig;

    @Override
    public String uploadFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("文件不能为空");
        }

        // 创建OSS客户端
        OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), 
                ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());

        try {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                throw new RuntimeException("文件名不能为空");
            }

            // 生成唯一的文件名
            String fileName = generateFileName(originalFilename);

            // 获取文件输入流
            InputStream inputStream = file.getInputStream();

            // 创建上传请求
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    ossConfig.getBucketName(), fileName, inputStream);

            // 上传文件
            PutObjectResult result = ossClient.putObject(putObjectRequest);

            // 构建文件访问URL
            String fileUrl = "https://" + ossConfig.getBucketName() + "." + ossConfig.getEndpoint() + "/" + fileName;

            log.info("文件上传成功，访问URL: {}", fileUrl);
            return fileUrl;

        } catch (IOException e) {
            log.error("文件上传失败", e);
            throw new RuntimeException("文件上传失败: " + e.getMessage());
        } finally {
            // 关闭OSS客户端
            ossClient.shutdown();
        }
    }

    @Override
    public boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    @Override
    public boolean isValidFileSize(MultipartFile file, int maxSizeInMB) {
        long maxSizeInBytes = maxSizeInMB * 1024 * 1024;
        return file.getSize() <= maxSizeInBytes;
    }

    @Override
    public boolean deleteFile(String fileUrl) {
        if (fileUrl == null || fileUrl.trim().isEmpty()) {
            throw new RuntimeException("文件URL不能为空");
        }

        // 创建OSS客户端
        OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), 
                ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());

        try {
            // 从URL中提取文件路径
            String filePath = extractFilePathFromUrl(fileUrl);
            
            // 检查文件是否存在
            boolean exists = ossClient.doesObjectExist(ossConfig.getBucketName(), filePath);
            if (!exists) {
                log.warn("要删除的文件不存在: {}", filePath);
                return false;
            }

            // 删除文件
            ossClient.deleteObject(ossConfig.getBucketName(), filePath);
            
            log.info("文件删除成功: {}", filePath);
            return true;

        } catch (Exception e) {
            log.error("文件删除失败: {}", fileUrl, e);
            throw new RuntimeException("文件删除失败: " + e.getMessage());
        } finally {
            // 关闭OSS客户端
            ossClient.shutdown();
        }
    }

    @Override
    public Map<String, Object> deleteFiles(List<String> fileUrls) {
        if (fileUrls == null || fileUrls.isEmpty()) {
            throw new RuntimeException("文件URL列表不能为空");
        }

        Map<String, Object> result = new HashMap<>();
        List<String> successUrls = new ArrayList<>();
        List<String> failedUrls = new ArrayList<>();

        for (String fileUrl : fileUrls) {
            try {
                boolean deleted = deleteFile(fileUrl);
                if (deleted) {
                    successUrls.add(fileUrl);
                } else {
                    failedUrls.add(fileUrl + " - 文件不存在");
                }
            } catch (Exception e) {
                failedUrls.add(fileUrl + " - " + e.getMessage());
            }
        }

        result.put("successUrls", successUrls);
        result.put("failedUrls", failedUrls);
        result.put("successCount", successUrls.size());
        result.put("failedCount", failedUrls.size());

        return result;
    }

    @Override
    public List<Map<String, Object>> listFiles(String prefix, int maxKeys) {
        if (maxKeys <= 0) {
            maxKeys = 100; // 默认最多返回100个
        }

        // 创建OSS客户端
        OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), 
                ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());

        try {
            com.aliyun.oss.model.ListObjectsRequest listObjectsRequest = 
                new com.aliyun.oss.model.ListObjectsRequest(ossConfig.getBucketName());
            
            if (prefix != null && !prefix.trim().isEmpty()) {
                listObjectsRequest.setPrefix(prefix);
            }
            listObjectsRequest.setMaxKeys(maxKeys);

            com.aliyun.oss.model.ObjectListing objectListing = ossClient.listObjects(listObjectsRequest);
            
            List<Map<String, Object>> fileList = new ArrayList<>();
            
            for (com.aliyun.oss.model.OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                Map<String, Object> fileInfo = new HashMap<>();
                fileInfo.put("fileName", objectSummary.getKey());
                fileInfo.put("fileSize", objectSummary.getSize());
                fileInfo.put("lastModified", objectSummary.getLastModified());
                fileInfo.put("fileUrl", "https://" + ossConfig.getBucketName() + "." + 
                           ossConfig.getEndpoint() + "/" + objectSummary.getKey());
                fileList.add(fileInfo);
            }

            log.info("获取文件列表成功，共{}个文件", fileList.size());
            return fileList;

        } catch (Exception e) {
            log.error("获取文件列表失败", e);
            throw new RuntimeException("获取文件列表失败: " + e.getMessage());
        } finally {
            // 关闭OSS客户端
            ossClient.shutdown();
        }
    }

    /**
     * 生成唯一的文件名
     * 格式：images/年月日/UUID.扩展名
     *
     * @param originalFilename 原始文件名
     * @return 生成的文件名
     */
    private String generateFileName(String originalFilename) {
        // 获取文件扩展名
        String extension = "";
        int dotIndex = originalFilename.lastIndexOf(".");
        if (dotIndex > 0) {
            extension = originalFilename.substring(dotIndex);
        }

        // 按日期创建文件夹
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String datePath = sdf.format(new Date());

        // 生成UUID文件名
        String uuid = UUID.randomUUID().toString().replace("-", "");

        return "images/" + datePath + "/" + uuid + extension;
    }

    /**
     * 从文件URL中提取文件路径
     *
     * @param fileUrl 完整的文件URL
     * @return 文件在OSS中的路径
     */
    private String extractFilePathFromUrl(String fileUrl) {
        try {
            // 如果传入的已经是文件路径，直接返回
            if (!fileUrl.startsWith("http")) {
                return fileUrl;
            }

            // 从URL中提取路径部分
            // 格式：https://bucket-name.endpoint/path/to/file
            String bucketDomain = ossConfig.getBucketName() + "." + ossConfig.getEndpoint();
            String expectedPrefix = "https://" + bucketDomain + "/";
            
            if (fileUrl.startsWith(expectedPrefix)) {
                return fileUrl.substring(expectedPrefix.length());
            } else {
                throw new RuntimeException("无效的文件URL格式");
            }
        } catch (Exception e) {
            throw new RuntimeException("解析文件URL失败: " + e.getMessage());
        }
    }

    /**
     * 事务性上传文件（支持回滚）
     * 
     * @param file 要上传的文件
     * @param dbOperation 数据库操作函数，返回是否成功
     * @return 上传成功后的文件访问URL
     */
    public String uploadFileWithTransaction(MultipartFile file, java.util.function.Supplier<Boolean> dbOperation) {
        String fileUrl = null;
        
        try {
            // 1. 先上传到OSS
            fileUrl = uploadFile(file);
            log.info("文件上传到OSS成功: {}", fileUrl);
            
            // 2. 执行数据库操作
            boolean dbSuccess = dbOperation.get();
            
            if (!dbSuccess) {
                // 数据库操作失败，回滚删除OSS文件
                log.warn("数据库操作失败，开始回滚删除OSS文件: {}", fileUrl);
                boolean deleted = deleteFile(fileUrl);
                if (deleted) {
                    log.info("OSS文件回滚删除成功: {}", fileUrl);
                } else {
                    log.error("OSS文件回滚删除失败: {}", fileUrl);
                }
                throw new RuntimeException("数据库操作失败，已回滚OSS文件");
            }
            
            log.info("文件上传和数据库操作都成功: {}", fileUrl);
            return fileUrl;
            
        } catch (Exception e) {
            // 如果是在数据库操作阶段失败且OSS文件已上传，尝试清理
            if (fileUrl != null && !e.getMessage().contains("数据库操作失败")) {
                try {
                    deleteFile(fileUrl);
                    log.info("异常时清理OSS文件成功: {}", fileUrl);
                } catch (Exception cleanupException) {
                    log.error("异常时清理OSS文件失败: {}", fileUrl, cleanupException);
                }
            }
            throw e;
        }
    }
} 