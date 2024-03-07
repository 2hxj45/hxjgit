package com.xz.library.admin.bean;

import java.util.function.Supplier;

/**
 * @author xz
 */
public class Validates {
    public static void isTrue(boolean expression, Supplier<RuntimeException> supplier) {
        if (expression) {
            throw supplier.get();
        }
    }

    public static void isTrue(boolean expression, String msg) {
        isTrue(expression, () -> new RuntimeException(msg));
    }

}
