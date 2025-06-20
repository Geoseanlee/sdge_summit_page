package com.sdgs.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.sdgs.config.OssConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 阿里云OSS文件服务接口
 */
public interface OssService {

    /**
     * 上传文件到阿里云OSS
     *
     * @param file 要上传的文件
     * @return 上传成功后的文件访问URL
     */
    String uploadFile(MultipartFile file);

    /**
     * 验证文件类型是否为图片
     *
     * @param file 要验证的文件
     * @return 是否为图片文件
     */
    boolean isImageFile(MultipartFile file);

    /**
     * 验证文件大小
     *
     * @param file 要验证的文件
     * @param maxSizeInMB 最大文件大小（MB）
     * @return 是否符合大小限制
     */
    boolean isValidFileSize(MultipartFile file, int maxSizeInMB);

    /**
     * 删除OSS中的文件
     *
     * @param fileUrl 文件的完整URL或文件路径
     * @return 是否删除成功
     */
    boolean deleteFile(String fileUrl);

    /**
     * 批量删除文件
     *
     * @param fileUrls 文件URL列表
     * @return 删除结果统计
     */
    Map<String, Object> deleteFiles(List<String> fileUrls);

    /**
     * 获取指定目录下的文件列表
     *
     * @param prefix 目录前缀，如 "images/2024/01/"
     * @param maxKeys 最大返回数量
     * @return 文件信息列表
     */
    List<Map<String, Object>> listFiles(String prefix, int maxKeys);

    /**
     * 事务性上传文件（支持回滚）
     * 先上传到OSS，然后执行数据库操作，如果数据库操作失败则回滚删除OSS文件
     *
     * @param file 要上传的文件
     * @param dbOperation 数据库操作函数，返回是否成功
     * @return 上传成功后的文件访问URL
     */
    default String uploadFileWithTransaction(MultipartFile file, java.util.function.Supplier<Boolean> dbOperation) {
        throw new UnsupportedOperationException("请在实现类中重写此方法");
    }
} 