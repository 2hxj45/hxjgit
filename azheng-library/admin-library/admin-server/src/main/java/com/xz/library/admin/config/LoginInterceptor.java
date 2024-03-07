package com.xz.library.admin.config;

import com.alibaba.fastjson.JSON;
import com.xz.library.admin.bean.LoginFailureException;
import com.xz.library.admin.bean.NoLogin;
import com.xz.library.admin.bean.TokenManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xz
 */
@Component
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof ResourceHttpRequestHandler) {
            // 资源处理器
            return true;
        }
        log.info("拦截器，请求uri为：{}，请求RequestParam参数：{}", request.getRequestURI(), JSON.toJSONString(request.getParameterMap()));
        // 如果 不是用xz开头的 则放行
        if (!request.getServletPath().startsWith("/xz")) {
            return true;
        }
        // 如果 方法上打了@NoLogin注解，放行
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            NoLogin noLogin = handlerMethod.getMethodAnnotation(NoLogin.class);
            if (noLogin != null) {
                return true;
            }
            // 如果 类上面打了@NoLogin注解，放行
            noLogin = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), NoLogin.class);
            if (noLogin != null) {
                return true;
            }
            //没有token抛异常
            if (tokenManager.getUser() == null) {
                throw new LoginFailureException();
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 最后执行 懒得写新方法了 token传空即可
        tokenManager.removeUser(null);
    }
}
