package com.xz.library.admin.bean;

import com.xz.library.admin.exception.ErrorCode;
import com.xz.library.admin.exception.ExceptionCode;

/**
 * @author xz
 */
public class LoginFailureException extends RuntimeException implements ExceptionCode {
    public LoginFailureException() {
    }

    public LoginFailureException(String message) {
        super(message);
    }

    @Override
    public ErrorCode getCode() {
        return ErrorCode.LOGIN_FAIL;
    }
}
