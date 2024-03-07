package com.xz.library.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xz.library.admin.entity.UserComment;
import com.xz.library.admin.entity.param.ChildCommentParam;
import com.xz.library.admin.entity.param.QueryGiveLikeRecordParam;
import com.xz.library.admin.entity.result.ChildCommentVO;
import com.xz.library.admin.entity.result.UserCommentVO;

import java.util.List;

/**
 * @author xz
 */
public interface UserCommentMapper extends BaseMapper<UserComment> {
    /**
     * 查出评论新闻的评论
     * 由点赞顺序由高到低排列,而且显示点赞个数和有几条子评论
     * 先根据点赞,点赞数相同根据时间降序
     *
     * @param param
     * @return
     */
    List<UserCommentVO> queryUserComment(QueryGiveLikeRecordParam param);

    /**
     * 查出子评论,根据时间降序排列
     *
     * @param commentId
     * @return
     */
    List<ChildCommentVO> queryUserForUserComment(Long commentId);

    /**
     * 根据评论id查出是否用户已点赞
     *
     * @param userId
     * @param commentId
     * @return
     */
    Integer queryWhetherGiveLikeById(Long userId, Long commentId);

}
