package ir.maktabsharif115.springboot.util;

public class CookieContextHolder {

    private static final ThreadLocal<String> COOKIE = new ThreadLocal<>();

    public static void setCookie(String value) {
        COOKIE.set(value);
    }

    public static String getCookie() {
        return COOKIE.get();
    }

    public static void clear() {
        COOKIE.remove();
    }

}
