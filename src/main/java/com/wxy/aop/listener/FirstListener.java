package com.wxy.aop.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author wxy
 * @create 2018-03-28 14:56
 * @desc ${DESCRIPTION}
 **/
@WebListener
public class FirstListener implements ServletContextListener {

    private static Logger LOG = LoggerFactory.getLogger(FirstListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("LOG:FirstListener init");
        LOG.info("FirstListener 初始化...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("LOG:FirstListener destroy");
        LOG.info("FirstListener 销毁...");
    }

}