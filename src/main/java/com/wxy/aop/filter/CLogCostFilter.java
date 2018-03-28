package com.wxy.aop.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author wxy
 * @create 2018-03-28 14:00
 * @desc ${DESCRIPTION}
 **/

/**
 * @WebFilter这个注解并没有指定执行顺序的属性，其执行顺序依赖于Filter的名称，是根据Filter类名
 * （注意不是配置的filter的名字）的字母顺序倒序排列，
 * 并且@WebFilter指定的过滤器优先级都高于FilterRegistrationBean配置的过滤器。
 */
@WebFilter(urlPatterns = "/*", filterName = "logFilter2")
public class CLogCostFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("PRINT:CLogCostFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println(""+"CLogCostFilter Execute cost=" + (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {
        System.out.println("PRINT:CLogCostFilter destroy");
    }
}