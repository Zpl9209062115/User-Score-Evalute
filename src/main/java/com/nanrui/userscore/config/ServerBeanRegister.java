package com.nanrui.userscore.config;

import com.nanrui.userscore.filter.FilterRegister;
import com.nanrui.userscore.listener.ListenerRegister;
import com.nanrui.userscore.servlet.ServletRegister;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @ClassName ServletBeanRegister
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/18 14:30
 * @Version 1.0
 **/
@Configuration
public class ServerBeanRegister {

    /**
     * 注册三大组件-Servlet
     * @return
     */
    /*@Bean
    public ServletRegistrationBean servletRegister(){
        //ServletRegistrationBean bean = new ServletRegistrationBean(new ServletRegister(),"/servlet");
        return null;
    }*/

    /**
     * 注册三大组件-listener
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean listenerRegister(){
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean<ListenerRegister>(new ListenerRegister());
        return bean;
    }

    /**
     * 注册三大组件-filter
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegister(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new FilterRegister());
        bean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return bean;
    }

}
