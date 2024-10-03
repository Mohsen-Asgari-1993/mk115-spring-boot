/*
package ir.maktabsharif115.springboot.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpServletRequest) {
            String servletPath = httpServletRequest.getServletPath();
            System.out.println(
                    "ServletPath: " + servletPath
            );
            if (servletPath.startsWith("/cat")) {
                String header = httpServletRequest.getHeader("my-custom-header");
                if (StringUtils.isBlank(header) || !"my-secure-header".equalsIgnoreCase(header)) {
                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                    httpServletResponse.setStatus(401);
                    return;
                }
            }
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setStatus(400);
        }
    }
}
*/
