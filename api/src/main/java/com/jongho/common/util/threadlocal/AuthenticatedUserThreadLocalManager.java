package com.jongho.common.util.threadlocal;

public class AuthenticatedUserThreadLocalManager {
    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void set(Long userId) {
        threadLocal.set(userId);
    }

    public static Long get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
