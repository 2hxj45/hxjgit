package com.xz.library.admin.service;

import com.xz.library.admin.entity.GiveLikeInfo;
import com.xz.library.admin.entity.param.QueryGiveLikeRecordParam;

/**
 * @author xz
 */
public interface GiveLikeInfoService {
    /**
     * 增加用户点赞信息
     *
     * @param info
     * @return
     */
    Boolean insertGiveLikeInfo(GiveLikeInfo info);

    /**
     * 取消点赞
     * 应该还需要查一次,确保已点赞或收藏才能进行操作
     *
     * @param info
     * @return
     */
    Boolean deleteGiveLikeInfo(GiveLikeInfo info);

    /**
     * 用户对新闻是否有点赞记录
     *
     * @return
     */
    Boolean whetherGiveLike(QueryGiveLikeRecordParam param);
}
