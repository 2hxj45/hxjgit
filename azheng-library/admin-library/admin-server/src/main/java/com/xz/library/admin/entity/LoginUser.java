package com.xz.library.admin.entity;

import lombok.Data;

/**
 * @author xz
 */
@Data
public class LoginUser {

    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
}
