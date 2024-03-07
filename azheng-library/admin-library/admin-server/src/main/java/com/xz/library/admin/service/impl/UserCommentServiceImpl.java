package com.xz.library.admin.service.impl;

import com.xz.library.admin.entity.UserComment;
import com.xz.library.admin.entity.param.ChildCommentParam;
import com.xz.library.admin.entity.param.QueryGiveLikeRecordParam;
import com.xz.library.admin.entity.result.ChildCommentVO;
import com.xz.library.admin.entity.result.UserCommentVO;
import com.xz.library.admin.mapper.UserCommentMapper;
import com.xz.library.admin.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xz
 */
@Service
public class UserCommentServiceImpl implements UserCommentService {
    @Autowired
    private UserCommentMapper userCommentMapper;

    @Override
    public Boolean saveUserComment(UserComment param) {
        int insert = userCommentMapper.insert(param);
        return insert > 0;
    }

    @Override
    public List<UserCommentVO> queryUserComment(QueryGiveLikeRecordParam param) {
        List<UserCommentVO> userCommentVOS = userCommentMapper.queryUserComment(param);
        userCommentVOS.forEach(userCommentVO -> {
            Long commentId = userCommentVO.getCommentId();
            Integer integer = userCommentMapper.queryWhetherGiveLikeById(param.getUserId(), commentId);
            if (integer > 0) {
                userCommentVO.setWhetherGiveLike(1);
            }
        });
        return userCommentVOS;
    }

    @Override
    public List<ChildCommentVO> queryUserForUserComment(ChildCommentParam param) {
        List<ChildCommentVO> childCommentVOS = userCommentMapper.queryUserForUserComment(param.getCommentId());
        return childCommentVOS;
    }
}
