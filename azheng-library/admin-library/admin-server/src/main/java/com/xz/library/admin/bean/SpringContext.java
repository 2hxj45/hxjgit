package com.xz.library.admin.bean;

import org.springframework.context.ApplicationContext;

/**
 * @author xz
 */
public class SpringContext {
    private static ApplicationContext ctx;

    public static <T> T getBean(Class<T> clazz) {
        return ctx.getBean(clazz);
    }

    public static Object getBean(String beanName) {
        return ctx.getBean(beanName);
    }

    public static void setApplicationContext(ApplicationContext ctx) {
        SpringContext.ctx = ctx;
    }

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }
}
