package com.xz.library.admin.bean;

import java.lang.annotation.*;

/**
 * @author xz
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoLogin {
}
