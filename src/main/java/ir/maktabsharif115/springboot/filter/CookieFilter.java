package ir.maktabsharif115.springboot.filter;

import ir.maktabsharif115.springboot.util.CookieContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class CookieFilter extends OncePerRequestFilter {

    public static final String COOKIE_NAME = "my-custom-cookie";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        CookieContextHolder.clear();
        boolean addCookie = true;
        String cookieValue = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_NAME)) {
                    addCookie = false;
                    cookieValue = cookie.getValue();
                    break;
                }
            }
        }

        if (addCookie) {
            cookieValue = UUID.randomUUID().toString();
            Cookie cookie = new Cookie(
                    COOKIE_NAME, cookieValue
            );
            cookie.setMaxAge(60 * 60 * 60);
//            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
//            cookie.set
            response.addCookie(cookie);
        }

        CookieContextHolder.setCookie(cookieValue);

        filterChain.doFilter(request, response);

    }
}
