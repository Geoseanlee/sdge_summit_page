package com.sdgs.service;

import com.sdgs.entity.AboutOverviewPage;

public interface AboutOverviewPageService {
    /** 前台读取（已发布、按 locale） */
    AboutOverviewPage getPublished(String locale);

    /** 后台读取草稿/已发 */
    AboutOverviewPage getById(Long id);

    /** 新建草稿 */
    Long create(AboutOverviewPage page);

    /** 更新（可把 status 一并改成 1=发布 0=草稿） */
    boolean update(AboutOverviewPage page);

    /** 删除 */
    boolean delete(Long id);
}
