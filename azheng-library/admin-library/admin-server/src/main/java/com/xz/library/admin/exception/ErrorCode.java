package com.xz.library.admin.exception;

/**
 * @author xz
 */
public enum  ErrorCode {
    LOGIN_FAIL("9", "登录失败");

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final String code;
    private final String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
