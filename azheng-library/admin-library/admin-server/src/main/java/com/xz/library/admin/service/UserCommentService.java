package com.xz.library.admin.service;


import com.xz.library.admin.entity.UserComment;
import com.xz.library.admin.entity.param.ChildCommentParam;
import com.xz.library.admin.entity.param.QueryGiveLikeRecordParam;
import com.xz.library.admin.entity.result.ChildCommentVO;
import com.xz.library.admin.entity.result.UserCommentVO;

import java.util.List;

/**
 * 用户评论相关
 *
 * @author xz
 */
public interface UserCommentService {
    /**
     * 保存用户评论
     *
     * @param param
     * @return
     */
    Boolean saveUserComment(UserComment param);

    /**
     * 查出评论新闻的评论
     *
     * @param param
     * @return
     */
    List<UserCommentVO> queryUserComment(QueryGiveLikeRecordParam param);

    /**
     * 查出子评论,根据时间降序排列
     *
     * @param param
     * @return
     */
    List<ChildCommentVO> queryUserForUserComment(ChildCommentParam param);
}
