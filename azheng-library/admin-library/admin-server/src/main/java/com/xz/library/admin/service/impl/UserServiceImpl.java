package com.xz.library.admin.service.impl;

import com.xz.library.admin.entity.LoginUser;
import com.xz.library.admin.entity.RegisterInfo;
import com.xz.library.admin.entity.UserInfo;
import com.xz.library.admin.mapper.UserAccountMapper;
import com.xz.library.admin.mapper.UserInfoMapper;
import com.xz.library.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xz
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserAccountMapper userAccountMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveUser(RegisterInfo info) {
        int insert = userAccountMapper.insert(info);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public LoginUser getLoginUser(String userName, String password) {
        return userInfoMapper.getLoginUser(userName, password);
    }
}
