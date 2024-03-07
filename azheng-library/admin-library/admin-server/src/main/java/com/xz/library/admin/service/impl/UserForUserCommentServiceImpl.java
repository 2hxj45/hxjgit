package com.xz.library.admin.service.impl;

import com.xz.library.admin.entity.UserForUserComment;
import com.xz.library.admin.mapper.UserForUserCommentMapper;
import com.xz.library.admin.service.UserForUserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xz
 */
@Service
public class UserForUserCommentServiceImpl implements UserForUserCommentService {
    @Autowired
    private UserForUserCommentMapper baseMapper;

    @Override
    public Boolean insertComment(UserForUserComment comment) {
        int insert = baseMapper.insert(comment);
        return insert > 0;
    }
}
