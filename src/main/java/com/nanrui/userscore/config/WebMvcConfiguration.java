package com.nanrui.userscore.config;

import com.nanrui.userscore.component.LoginHandleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName WebMvcConfigura
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/15 11:31
 * @Version 1.0
 **/
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
                registry.addViewController("/userRatings").setViewName("/emp/add");
                registry.addViewController("/userList").setViewName("/emp/list");
                registry.addViewController("/emp/view.html").setViewName("/emp/view");
                registry.addViewController("/emp/model").setViewName("/emp/model");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //静态资源：*.css *.js  SpringBoot已经做好了静态资源映射
                System.out.println("进入拦截器");
                registry.addInterceptor(new LoginHandleInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/index.html","/user/login");
            }
        };
        return adapter;
    }

}
