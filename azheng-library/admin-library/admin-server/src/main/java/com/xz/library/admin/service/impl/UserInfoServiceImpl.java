package com.xz.library.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.library.admin.entity.RegisterInfo;
import com.xz.library.admin.entity.UserInfo;
import com.xz.library.admin.mapper.UserAccountMapper;
import com.xz.library.admin.mapper.UserInfoMapper;
import com.xz.library.admin.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiezheng
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    public List<UserInfo> queryUserInfo() {
        List<UserInfo> userInfos = userInfoMapper.selectList(null);
        return userInfos;
    }

    @Override
    public RegisterInfo getUserInfo(Long userId) {
        RegisterInfo registerInfo = userAccountMapper.selectById(userId);
        return registerInfo;
    }
}
