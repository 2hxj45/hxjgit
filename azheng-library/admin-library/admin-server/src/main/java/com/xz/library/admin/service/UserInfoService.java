package com.xz.library.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xz.library.admin.entity.RegisterInfo;
import com.xz.library.admin.entity.UserInfo;

import java.util.List;

/**
 * @author xz
 */
public interface UserInfoService {
    public List<UserInfo> queryUserInfo();

    /**
     * 根据userId查出全部信息
     *
     * @param userId
     */
    RegisterInfo getUserInfo(Long userId);
}
