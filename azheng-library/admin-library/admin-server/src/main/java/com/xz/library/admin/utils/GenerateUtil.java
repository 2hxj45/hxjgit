package com.xz.library.admin.utils;

import org.springframework.util.DigestUtils;

/**
 * 生成东西的工具类
 *
 * @author xz
 */
public class GenerateUtil {
    public static String getUserPassword(String data) {

        byte[] bytes = data.getBytes();
        return DigestUtils.md5DigestAsHex(bytes);
    }
}
