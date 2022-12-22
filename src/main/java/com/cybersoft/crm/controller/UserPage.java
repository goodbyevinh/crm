package com.cybersoft.crm.controller;

import com.cybersoft.crm.dto.UserDTO;
import com.cybersoft.crm.dto.UserDetailDTO;
import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.model.TaskModel;
import com.cybersoft.crm.model.UserModel;
import com.cybersoft.crm.service.BaseService;
import com.cybersoft.crm.service.RoleService;
import com.cybersoft.crm.service.TaskService;
import com.cybersoft.crm.service.UserService;
import com.cybersoft.crm.utils.PathEnum;
import com.cybersoft.crm.utils.Url;

import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "user", urlPatterns = {
        Url.USER,
        Url.ADD_USER,
        Url.USER_DETAIL,
        Url.UPDATE_USER
})
public class UserPage extends HttpServlet {
    BaseService baseService = UserService.getINSTANCE();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case Url.USER:{
                List<UserDTO> users = baseService.getAllModel();
                req.setAttribute("users", users);
                req.getRequestDispatcher(PathEnum.USER.getPath()).forward(req, resp);
                break;
            }
            case Url.ADD_USER: {
                List<RoleModel> roles = RoleService.getINSTANCE().getAllModel();
                req.setAttribute("roles", roles);
                req.getRequestDispatcher(PathEnum.ADD_USER.getPath()).forward(req, resp);
                break;
            }
            case Url.USER_DETAIL: {
                if (req.getParameter("id") != null) {
                    int id = Integer.parseInt(req.getParameter("id"));
                    UserDetailDTO userDetail = UserService.getINSTANCE().findUserDetailById(id);
                    List<TaskModel> tasks = TaskService.getINSTANCE().findTasksById(id);
                    if (userDetail != null ) {
                        req.setAttribute("userDetail", userDetail);
                        req.setAttribute("tasks", tasks);
                        req.getRequestDispatcher(PathEnum.USER_DETAIL.getPath()).forward(req, resp);
                    } else {
                        req.getRequestDispatcher(PathEnum.BLANK.getPath()).forward(req,resp);
                    }
                } else {
                    req.getRequestDispatcher(PathEnum.BLANK.getPath()).forward(req, resp);
                }
                break;
            }
            case Url.UPDATE_USER: {
                if (req.getParameter("id") != null) {
                    int id = Integer.parseInt(req.getParameter("id"));
                    UserModel user = UserService.getINSTANCE().findUserById(id);
                    List<RoleModel> roles = RoleService.getINSTANCE().getAllModel();
                    if (user != null) {
                        req.setAttribute("user",UserService.getINSTANCE().findUserById(id));
                        req.setAttribute("roles", RoleService.getINSTANCE().getAllModel());
                        req.getRequestDispatcher(PathEnum.UPDATE_USER.getPath()).forward(req, resp);

                    } else {
                        req.getRequestDispatcher(PathEnum.BLANK.getPath()).forward(req,resp);
                    }
                } else {
                    req.getRequestDispatcher(PathEnum.BLANK.getPath()).forward(req, resp);
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
