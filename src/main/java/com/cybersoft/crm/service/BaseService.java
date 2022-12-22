package com.cybersoft.crm.service;

import com.cybersoft.crm.model.RoleModel;

import java.util.List;

public interface BaseService <T>{
    List<?> getAllModel();
    boolean deleteModelById(int id);
    boolean insertModel(T model);
    boolean updateModel(T model);

}
