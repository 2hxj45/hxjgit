package com.xz.library.admin.bean;

import com.xz.library.admin.entity.LoginUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xz
 */
public class UserContext {
    public static final String HEADER_TOKEN = "token";

    public static LoginUser getLoginUser() {
        return getLoginUser(getRequest());
    }

    public static LoginUser getLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        return SpringContext.getBean(TokenManager.class).getUser(token);
    }

    public static String getToken(HttpServletRequest request) {
        return request.getHeader(HEADER_TOKEN);
    }

    public static String getToken() {
        return getToken(getRequest());
    }

    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    }
}
