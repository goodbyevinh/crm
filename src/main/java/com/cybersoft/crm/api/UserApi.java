package com.cybersoft.crm.api;


import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.model.UserModel;
import com.cybersoft.crm.payload.ResponseData;
import com.cybersoft.crm.service.BaseService;
import com.cybersoft.crm.service.UserService;
import com.cybersoft.crm.utils.Url;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "userApi", urlPatterns = {
        Url.API_USER_ALL,
})
public class UserApi extends HttpServlet {

    BaseService baseService = UserService.getINSTANCE();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        switch (pathInfo) {
            case Url.API_DELETE: {
                int id = Integer.parseInt(req.getParameter("id"));
                boolean isSuccess = baseService.deleteModelById(id);
                ResponseData responseData = new ResponseData();
                responseData.setStatusCode(200);
                responseData.setSuccess(isSuccess);
                responseData.setDescription(isSuccess ? "delete success" : "delete fail");

                Gson gson = new Gson();
                String json = gson.toJson(responseData);
                out.print(json);
                out.flush();
                break;
            }
            case Url.API_UPDATE: {
                Gson gson = new Gson();
                UserModel user = gson.fromJson(req.getReader(), UserModel.class);

                boolean isSuccess = baseService.updateModel(user);

                ResponseData responseData = new ResponseData();
                responseData.setStatusCode(200);
                responseData.setSuccess(isSuccess);
                responseData.setDescription(isSuccess ? "update success" : "update fail");


                String json = gson.toJson(responseData);
                out.print(json);
                out.flush();
                break;
            }

            case Url.API_ADD: {
                Gson gson = new Gson();
                UserModel user = gson.fromJson(req.getReader(), UserModel.class);
                boolean isSuccess = baseService.insertModel(user);

                ResponseData responseData = new ResponseData();
                responseData.setStatusCode(200);
                responseData.setSuccess(isSuccess);
                responseData.setDescription(isSuccess ? "insert success" : "insert fail");


                String json = gson.toJson(responseData);
                out.print(json);
                out.flush();
                break;
            }

            default: {
                ResponseData responseData = new ResponseData();
                responseData.setStatusCode(400);
                responseData.setDescription("error url Api");
                Gson gson = new Gson();
                String json = gson.toJson(responseData);
                out.print(json);
                out.flush();
            }
        }
    }
}
