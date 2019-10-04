package test;

import javax.servlet.*;
import java.io.IOException;

public class test_filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
/**
 * 顺带加个listen
 */
    }

    @Override
    public void destroy() {

    }
}
