package com.nanrui.userscore.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @ClassName ListenerRegister
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/18 14:27
 * @Version 1.0
 **/
public class ListenerRegister implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("监听web项目启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("监听web项目销毁");
    }
}
