package com.wxy.aop.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wxy
 * @create 2018-03-28 13:59
 * @desc filter配置方法一
 **/
@Configuration
public class MyFilterConfig {
    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new BLogCostFilter());
        registration.addUrlPatterns("/*");
        registration.setName("BLogCostFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean registFilter2() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DLogCostFilter());
        registration.addUrlPatterns("/*");
        registration.setName("DLogCostFilter");
        registration.setOrder(2);
        return registration;
    }
}
