package com.xz.library.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.library.admin.entity.GiveLikeInfo;
import com.xz.library.admin.entity.param.QueryGiveLikeRecordParam;
import com.xz.library.admin.mapper.GiveLikeInfoMapper;
import com.xz.library.admin.service.GiveLikeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xz
 */
@Service
public class GiveLikeInfoServiceImpl implements GiveLikeInfoService {
    @Autowired
    GiveLikeInfoMapper baseMapper;

    @Override
    public Boolean insertGiveLikeInfo(GiveLikeInfo info) {
        int insert = baseMapper.insert(info);
        return insert > 0;
    }

    @Override
    public Boolean deleteGiveLikeInfo(GiveLikeInfo info) {
        QueryWrapper<GiveLikeInfo> giveLikeInfoQueryWrapper = new QueryWrapper<>(info);
        int delete = baseMapper.delete(giveLikeInfoQueryWrapper);
        return delete > 0;
    }

    @Override
    public Boolean whetherGiveLike(QueryGiveLikeRecordParam param) {
        Integer integer = baseMapper.whetherGiveLike(param);
        return integer > 0;
    }
}
