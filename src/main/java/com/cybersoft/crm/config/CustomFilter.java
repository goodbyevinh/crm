package com.cybersoft.crm.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class CustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //response.sendRedirect(); chỉ dùng 1 trong 2
        servletRequest.setCharacterEncoding("UTF-8"); // chỉ dùng cho html , ko dùng jsp
        filterChain.doFilter(servletRequest, servletResponse); // cho phép đi vào link mong muốn
    }
}
