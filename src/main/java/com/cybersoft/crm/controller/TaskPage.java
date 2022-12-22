package com.cybersoft.crm.controller;

import com.cybersoft.crm.model.TaskModel;
import com.cybersoft.crm.model.UserModel;
import com.cybersoft.crm.service.*;
import com.cybersoft.crm.utils.PathEnum;
import com.cybersoft.crm.utils.Url;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@WebServlet(name = "task", urlPatterns = {
        Url.TASK,
        Url.ADD_TASK,
        Url.UPDATE_TASK
})
public class TaskPage extends HttpServlet {

    BaseService baseService = TaskService.getINSTANCE();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case Url.TASK:{
                List<TaskModel> tasks = baseService.getAllModel();
                req.setAttribute("tasks", tasks);
                req.getRequestDispatcher(PathEnum.TASK.getPath()).forward(req, resp);
                break;
            }
            case Url.ADD_TASK: {
                req.setAttribute("jobs", JobService.getINSTANCE().getAllModel());
                req.setAttribute("users", UserService.getINSTANCE().getAllModel());
                req.getRequestDispatcher(PathEnum.ADD_TASK.getPath()).forward(req, resp);
                break;
            }
            case Url.UPDATE_TASK: {
                if(req.getParameter("id") != null) {
                    int id = Integer.parseInt(req.getParameter("id"));
                    TaskModel task = TaskService.getINSTANCE().findTaskById(id);
                    if (task != null) {
                        req.setAttribute("task", task);
                        req.setAttribute("jobs", JobService.getINSTANCE().getAllModel());
                        req.setAttribute("users", UserService.getINSTANCE().getAllModel());
                        req.getRequestDispatcher(PathEnum.UPDATE_TASK.getPath()).forward(req, resp);
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
