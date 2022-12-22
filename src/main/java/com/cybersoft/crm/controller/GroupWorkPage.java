package com.cybersoft.crm.controller;

import com.cybersoft.crm.dto.JobDetailDTO;
import com.cybersoft.crm.model.JobModel;
import com.cybersoft.crm.service.BaseService;
import com.cybersoft.crm.service.JobService;
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

@WebServlet(name = "groupwork", urlPatterns = {
        Url.JOB,
        Url.ADD_JOB,
        Url.JOB_DETAIL,
        Url.UPDATE_JOB
})
public class GroupWorkPage extends HttpServlet {
    private BaseService baseService = JobService.getINSTANCE();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (req.getServletPath()) {
            case Url.JOB:{
                req.setAttribute("jobs", baseService.getAllModel());
                req.getRequestDispatcher(PathEnum.JOB.getPath()).forward(req, resp);
                break;
            }
            case Url.ADD_JOB: {
                req.getRequestDispatcher(PathEnum.ADD_JOB.getPath()).forward(req, resp);
                break;
            }
            case Url.UPDATE_JOB: {
                if (req.getParameter("id") != null) {
                    int id = Integer.parseInt(req.getParameter("id"));
                    JobModel job = JobService.getINSTANCE().findJobById(id);
                    if (job != null) {
                        req.setAttribute("job", job);
                        req.getRequestDispatcher(PathEnum.UPDATE_JOB.getPath()).forward(req, resp);
                    }
                    else {
                        req.getRequestDispatcher(PathEnum.NOT_FOUND.getPath()).forward(req, resp);
                    }
                } else {
                    req.getRequestDispatcher(PathEnum.NOT_FOUND.getPath()).forward(req, resp);
                }

                break;
            }
            case Url.JOB_DETAIL: {
                if (req.getParameter("id") != null) {
                    int id = Integer.parseInt(req.getParameter("id"));
                    JobDetailDTO jobDetailDTO = JobService.getINSTANCE().findProfileJobById(id);
                    req.setAttribute("jobDetail", jobDetailDTO);
                    req.setAttribute("users", UserService.getINSTANCE().findTaskInUsersByJobId(id));
                    req.getRequestDispatcher(PathEnum.JOB_DETAIL.getPath()).forward(req, resp);
                } else {
                    req.getRequestDispatcher(PathEnum.NOT_FOUND.getPath()).forward(req, resp);
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
