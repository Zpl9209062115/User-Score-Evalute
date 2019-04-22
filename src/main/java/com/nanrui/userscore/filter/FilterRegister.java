package com.nanrui.userscore.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName FilterRegister
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/18 14:27
 * @Version 1.0
 **/
public class FilterRegister implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter执行");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
