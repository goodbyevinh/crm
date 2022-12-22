package com.cybersoft.crm.controller;

import com.cybersoft.crm.utils.PathEnum;
import com.cybersoft.crm.utils.Url;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "error", urlPatterns = {
        Url.NOT_FOUND,
        Url.BLANK
})
public class ErrorPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case Url.NOT_FOUND:{
                req.getRequestDispatcher(PathEnum.NOT_FOUND.getPath()).forward(req, resp);
                break;
            }
            case Url.BLANK: {
                req.getRequestDispatcher(PathEnum.BLANK.getPath()).forward(req, resp);
                break;
            }
        }
    }
}
