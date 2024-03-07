package com.xz.library.admin.service;

import com.xz.library.admin.entity.LoginUser;
import com.xz.library.admin.entity.RegisterInfo;

/**
 * 和用户操作有关
 *
 * @author xz
 */
public interface UserService {
    /**
     * 注册  报存用户信息
     *
     * @param info
     * @return
     */
    public boolean saveUser(RegisterInfo info);

    /**
     * 登录时验证数据库是否有
     *
     * @param userName
     * @param password
     * @return
     */
    LoginUser getLoginUser(String userName, String password);

}
