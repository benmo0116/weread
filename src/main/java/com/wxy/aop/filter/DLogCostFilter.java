package com.wxy.aop.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author wxy
 * @create 2018-03-28 14:00
 * @desc ${DESCRIPTION}
 **/
public class DLogCostFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("PRINT:DLogCostFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("PRINT:"+"DLogCostFilter Execute cost=" + (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {
        System.out.println("PRINT:DLogCostFilter destroy");
    }
}