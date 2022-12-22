package com.cybersoft.crm.controller;

import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.model.UserModel;
import com.cybersoft.crm.service.BaseService;
import com.cybersoft.crm.service.RoleService;
import com.cybersoft.crm.utils.PathEnum;
import com.cybersoft.crm.utils.Url;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "role", urlPatterns = {
        Url.ROLE,
        Url.ADD_ROLE,
        Url.UPDATE_ROLE
})
public class RolePage extends HttpServlet {
    private BaseService baseService =  RoleService.getINSTANCE();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case Url.ROLE:{
                List<RoleModel> roles = baseService.getAllModel();
                if (roles != null ) {
                    req.setAttribute("roles",roles);
                    req.getRequestDispatcher(PathEnum.ROLE.getPath()).forward(req,resp);
                } else {
                    req.getRequestDispatcher(PathEnum.BLANK.getPath()).forward(req,resp);
                }
                break;
            }
            case Url.ADD_ROLE: {
                req.getRequestDispatcher(PathEnum.ADD_ROLE.getPath()).forward(req, resp);
                break;
            }
            case Url.UPDATE_ROLE: {
                if (req.getParameter("id") != null) {
                    int id = Integer.parseInt(req.getParameter("id"));
                    RoleModel role = RoleService.getINSTANCE().findRoleById(id);
                    if (role != null) {
                        req.setAttribute("role", role);
                        req.getRequestDispatcher(PathEnum.UPDATE_ROLE.getPath()).forward(req, resp);
                    } else {
                        req.getRequestDispatcher(PathEnum.BLANK.getPath()).forward(req,resp);
                    }
                } else {
                    req.getRequestDispatcher(PathEnum.BLANK.getPath()).forward(req,resp);
                }
                break;
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
