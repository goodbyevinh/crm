package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.LoginService;
import com.cybersoft.crm.utils.PathEnum;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "loginPage", urlPatterns = {"/login"})
public class LoginPage extends HttpServlet {

    private LoginService loginService = new LoginService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean isLogin = loginService.checkLogin(email, password);

//        Cookie cookie = new Cookie("email", email);
//        cookie.setMaxAge(5 * 60);
//        resp.addCookie(cookie);
//
//        Cookie cookie1 = new Cookie("password", password);
//        cookie.setMaxAge(5 * 60);
//        resp.addCookie(cookie1);

//        Cookie[] cookies = req.getCookies();
//
//        for (Cookie cookie: cookies) {
//            System.out.println("Name cookie " + cookie.getName());
//            System.out.println("Value cookie " + cookie.getValue());
//        }

        // yều cầu sử dụng session hoặc khởi tạo
        HttpSession session = req.getSession();
//        session.setAttribute("email", email);
//        session.setAttribute("password", password);
//        session.setMaxInactiveInterval(5 * 60);
        System.out.println(session.getAttribute("email"));
        System.out.println(session.getAttribute("password"));


        req.getRequestDispatcher(PathEnum.LOGIN.getPath()).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
