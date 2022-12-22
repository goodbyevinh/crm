package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.JobModel;
import com.cybersoft.crm.model.ProfileModel;
import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.model.UserModel;

import javax.management.relation.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends AbstractRepository<UserModel>{
// Đối với câu lấy giá trị : select => execute Query
//                           insert, update, delete => execute Update
    public List<UserModel> getUsersByEmailAndPassword(String email, String password) {
        List<UserModel> userModelList = new ArrayList<>();
        try {
            String query = "select * from users u where u.email = ? and u.password = ?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserModel userModel = new UserModel();
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                //usersModel.setPassword(resultSet.getString("password"));
                userModel.setAvatar(resultSet.getString("avatar"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModelList.add(userModel);
            }
            connection.close();
        }
        catch (Exception e) {
            System.out.println("Error getUsersByEmailAndPassword" + e.getMessage());
        }
        return userModelList;
    }
    public List<UserModel> getAllUser() {
        List <UserModel> users = new ArrayList<>();
        String query = "select u.id, u.email, u.fullname, r.name as role from users u join roles r on u.role_id = r.id";
        return executeListQuery( connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserModel userModel = new UserModel();
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullname"));
                RoleModel role = new RoleModel();
                role.setName(resultSet.getString("role"));
                userModel.setRole(role);
                users.add(userModel);
            }
            return users;
        });
    }

    public int deleteUserById(int id) {
        String query = "delete from users u where u.id = ?";
        return executeUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        });
    }
    public int insertUser(UserModel user) {
        String query = "insert into users( fullname, email, password, phone_number, role_id) values (?, ?, ?, ?, ?)";
        return executeUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFullname());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setInt(5, user.getRole().getId());
            return preparedStatement.executeUpdate();
        });
    }

    public int updateUser(UserModel user) {
        String query = "update users set fullname = ?, email = ?, password = ?, phone_number = ?, role_id = ? where id = ?";
        return executeUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFullname());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setInt(5, user.getRole().getId());
            preparedStatement.setInt(6, user.getId());
            return preparedStatement.executeUpdate();
        });
    }

    public UserModel findUserById(int id) {
        String query = "select u.id, u.fullname , u.email, u.password, u.phone_number, r.id as roleId, r.name as roleName from users u join roles r on r.id = u.role_id where u.id = ?";
        return executeQuery(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            UserModel user = null;
            while (result.next()) {
                user = new UserModel();
                user.setId(result.getInt("id"));
                user.setFullname(result.getString("fullname"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setPhoneNumber((result.getString("phone_number")));
                RoleModel role = new RoleModel();
                role.setName(result.getString("roleName"));
                role.setId(result.getInt("roleId"));
                user.setRole(role);
            }
            return user;
        });
    }

    public UserModel findProfileTaskById(int id) {
        String query = " select u.id, u.fullname, u.email, count( distinct if(s.id = 1, t.id, null)) as yetStartTask, count( distinct if(s.id = 2, t.id, null)) as doingTask, count( distinct if(s.id = 3, t.id, null)) as completedTask, count(t.id) as totalTask" +
                " from tasks t" +
                " right join users u on t.user_id = u.id" +
                " left join status s on t.status_id = s.id" +
                " where u.id = ?" +
                " group by u.id";
        return executeQuery(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            UserModel user = null;
            while (result.next()) {
                user = new UserModel();
                user.setFullname(result.getString("fullname"));
                user.setId(result.getInt("id"));
                user.setEmail(result.getString("email"));
                ProfileModel profile = new ProfileModel();
                profile.setYetStartTask(result.getInt("yetStartTask"));
                profile.setDoingTask(result.getInt("doingTask"));
                profile.setCompletedTask(result.getInt("completedTask"));
                profile.setTotalTask(result.getInt("totalTask"));
                user.setProfile(profile);
            }
            return user;
        });
    }
    public List<UserModel> findUsersByJobId(int id ) {
        String query = "select u.id, u.fullname" +
                " from tasks t" +
                " join users u on t.user_id = u.id" +
                " where t.job_id = ?" +
                " group by u.id";
        List<UserModel> users = new ArrayList<>();
        return executeListQuery( connection ->  {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
               UserModel user = new UserModel();
               user.setId(result.getInt("id"));
               user.setFullname(result.getString("fullname"));
               users.add(user);
            }
            return users;
        });
    }
}
