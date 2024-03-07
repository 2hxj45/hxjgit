package com.xz.library.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xz.library.admin.entity.CollectInfo;
import com.xz.library.admin.entity.param.QueryGiveLikeRecordParam;
import com.xz.library.admin.entity.result.MyCollectVO;

import java.util.List;

/**
 * @author xz
 */
public interface CollectInfoMapper extends BaseMapper<CollectInfo> {
    /**
     * 根据用户id查出其收藏的新闻
     *
     * @param userId
     * @return
     */
    List<MyCollectVO> queryMyCollect(Long userId);

    /**
     * 查出子评论信息
     * 还未加点赞
     * 点赞表的设计应该加个类型
     */

    /**
     * 是否有该用户对某新闻的收藏记录
     * 根据userId和newsId查出该用户是否对这个新闻进行收藏
     *
     * @param param
     * @return
     */
    Integer whetherCollect(QueryGiveLikeRecordParam param);
}
