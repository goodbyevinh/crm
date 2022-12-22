package com.cybersoft.crm.controller;

import com.cybersoft.crm.dto.UserDetailDTO;
import com.cybersoft.crm.model.TaskModel;
import com.cybersoft.crm.model.UserModel;
import com.cybersoft.crm.service.TaskService;
import com.cybersoft.crm.service.UserService;
import com.cybersoft.crm.utils.PathEnum;
import com.cybersoft.crm.utils.Url;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "profile", urlPatterns = {
        Url.PROFILE,
        Url.PROFILE_EDIT
})
public class ProfilePage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case Url.PROFILE:{
                if (req.getParameter("id") !=  null) {
                    int id = Integer.parseInt(req.getParameter("id"));
                    UserDetailDTO userDetail = UserService.getINSTANCE().findUserDetailById(id);
                    List<TaskModel> tasks = TaskService.getINSTANCE().findTasksById(id);
                    if (userDetail !=  null) {
                        req.setAttribute("userDetail", userDetail);
                        req.setAttribute("tasks", tasks);
                        req.getRequestDispatcher(PathEnum.PROFILE.getPath()).forward(req, resp);
                    } else {
                        req.getRequestDispatcher(PathEnum.BLANK.getPath()).forward(req, resp);
                    }

                } else {
                    req.getRequestDispatcher(PathEnum.BLANK.getPath()).forward(req, resp);
                }
                break;
            }
            case Url.PROFILE_EDIT: {
                if (req.getParameter("id") != null) {
                    int taskId = Integer.parseInt(req.getParameter("id"));
                    TaskModel task = TaskService.getINSTANCE().findTaskById(taskId);
                    if (task != null) {
                        req.setAttribute("task", TaskService.getINSTANCE().findTaskById(taskId));
                        req.getRequestDispatcher(PathEnum.PROFILE_EDIT.getPath()).forward(req, resp);
                    } else {
                        req.getRequestDispatcher(PathEnum.BLANK.getPath()).forward(req, resp);
                    }
                } else {
                    req.getRequestDispatcher(PathEnum.BLANK.getPath()).forward(req, resp);
                }
                break;
            }
        }
    }
}
