package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.RoleModel;

import javax.management.relation.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends AbstractRepository<RoleModel>{
    public List<RoleModel> getAllRoles() {
        List<RoleModel> roles = new ArrayList<>();
        String query = "select * from roles";
        return executeListQuery( connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RoleModel role = new RoleModel();
                role.setId(Integer.parseInt(resultSet.getString("id")));
                role.setName(resultSet.getString("name"));
                role.setDescription(resultSet.getString("description"));
                roles.add(role);
            }
            return roles;
        });
    }
    public int deleteRoleById(int id) {
        String query = "delete from roles r where r.id = ?";
        return executeUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        });
    }

    public int insertRole(RoleModel role) {
        String query = "insert into roles( name, description ) values (?, ?)";
        return executeUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, role.getName());
            preparedStatement.setString(2, role.getDescription());
            return preparedStatement.executeUpdate();
        });
    }

    public int updateRole(RoleModel role) {
        String query = "update roles set name = ?, description = ? where id = ?";

        return executeUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, role.getName());
            preparedStatement.setString(2, role.getDescription());
            preparedStatement.setInt(3, role.getId());
            return preparedStatement.executeUpdate();
        });
    }
    public RoleModel findRoleById(int id) {
        String query = "select * from roles where id = ?";

        return executeQuery(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            RoleModel role = null;
            while (resultSet.next()) {
                role = new RoleModel();
                role.setId(Integer.parseInt(resultSet.getString("id")));
                role.setName(resultSet.getString("name"));
                role.setDescription(resultSet.getString("description"));
            }
            return role;
        });
    }
}
