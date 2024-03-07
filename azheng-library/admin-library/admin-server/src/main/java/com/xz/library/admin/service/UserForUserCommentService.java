package com.xz.library.admin.service;

import com.xz.library.admin.entity.UserForUserComment;

/**
 * 用户评论用户表对应服务层
 *
 * @author xz
 */
public interface UserForUserCommentService {
    /**
     * 增加用户评论用户
     *
     * @param comment
     * @return
     */
    Boolean insertComment(UserForUserComment comment);
}
