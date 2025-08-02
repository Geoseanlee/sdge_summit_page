package com.sdgs.service;

import com.sdgs.entity.JoinUsContent;
import java.util.List;

public interface JoinUsContentService {

    /**
     * 按类别查询内容
     */
    List<JoinUsContent> listByCategory(String category);

    /**
     * 新增内容
     */
    boolean add(JoinUsContent content);

    /**
     * 删除内容
     */
    boolean delete(Long id);

    /**
     * 更新内容
     */
    boolean update(JoinUsContent content);
}
