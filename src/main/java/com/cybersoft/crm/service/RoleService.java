package com.cybersoft.crm.service;

import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.repository.RoleRepository;

import javax.management.relation.Role;
import java.util.List;

public class RoleService implements BaseService<RoleModel>{
    private RoleRepository roleRepository = new RoleRepository();

    public static RoleService INSTANCE = null;

    public static RoleService getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new RoleService();
        return INSTANCE;
    }

    public RoleModel findRoleById(int id) {
        return roleRepository.findRoleById(id);
    }

    @Override
    public List<RoleModel> getAllModel() {
        return roleRepository.getAllRoles();
    }

    @Override
    public boolean deleteModelById(int id) {
        int result = roleRepository.deleteRoleById(id);
        return result > 0 ? true : false ;
    }

    @Override
    public boolean insertModel(RoleModel model) {
        int result = roleRepository.insertRole(model);
        return result > 0 ? true : false ;
    }

    @Override
    public boolean updateModel(RoleModel model) {
        int result = roleRepository.updateRole(model);
        return result > 0 ? true : false ;
    }
}
