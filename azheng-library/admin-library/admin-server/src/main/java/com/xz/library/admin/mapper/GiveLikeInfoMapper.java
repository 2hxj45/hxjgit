package com.xz.library.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xz.library.admin.entity.GiveLikeInfo;
import com.xz.library.admin.entity.param.QueryGiveLikeRecordParam;

/**
 * @author xz
 */
public interface GiveLikeInfoMapper extends BaseMapper<GiveLikeInfo> {
    /**
     * 是否有该用户对某新闻的点赞记录
     * 根据userId和newsId查出该用户是否对这个新闻进行点赞
     *
     * @param param
     * @return
     */
    Integer whetherGiveLike(QueryGiveLikeRecordParam param);
}
