package com.cybersoft.crm.service;

import com.cybersoft.crm.dto.UserDTO;
import com.cybersoft.crm.dto.UserDetailDTO;
import com.cybersoft.crm.dto.UserJobDetailDTO;
import com.cybersoft.crm.model.TaskModel;
import com.cybersoft.crm.model.UserModel;
import com.cybersoft.crm.repository.TaskRepository;
import com.cybersoft.crm.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService implements BaseService<UserModel>{
    UserRepository userRepository = new UserRepository();
    TaskRepository taskRepository = new TaskRepository();

    public static UserService INSTANCE = null;

    public static UserService getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new UserService();
        return INSTANCE;
    }
    @Override
    public List<UserDTO> getAllModel() {
        List<UserModel> users = userRepository.getAllUser();
        if (users == null )
            return null;
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserModel data : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(data.getId());
            userDTO.setRole(data.getRole().getName());
            userDTO.setFullname(data.getFullname());
            UserDTO.setUserName(data, userDTO);
            UserDTO.setFirstNameLastName(data, userDTO);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }
    @Override
    public boolean deleteModelById(int id) {
        int result = userRepository.deleteUserById(id);
        return result > 0 ? true : false ;
    }
    @Override
    public boolean insertModel(UserModel model) {
        int result = userRepository.insertUser(model);
        return result > 0 ? true : false ;
    }
    @Override
    public boolean updateModel(UserModel model) {
        int result = userRepository.updateUser(model);
        return result > 0 ? true : false ;
    }
    public UserModel findUserById(int id) {
        return userRepository.findUserById(id);
    }
    public UserDetailDTO findUserDetailById(int id) {
        UserModel user = userRepository.findProfileTaskById(id);
        if (user == null) {
            return null;
        }
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setEmail(user.getEmail());
        userDetailDTO.setFullname(user.getFullname());
        if (user.getProfile().getTotalTask() == 0) {
            userDetailDTO.setCompletedRate(0);
            userDetailDTO.setDoingRate(0);
            userDetailDTO.setYetRate(0);
        } else {
            userDetailDTO.setCompletedRate(user.getProfile().getCompletedTask() * 100 / user.getProfile().getTotalTask());
            userDetailDTO.setDoingRate(user.getProfile().getDoingTask() * 100 / user.getProfile().getTotalTask());
            userDetailDTO.setYetRate(user.getProfile().getYetStartTask() * 100 / user.getProfile().getTotalTask());
        }

        return userDetailDTO;
    }
    public List<UserJobDetailDTO> findTaskInUsersByJobId(int jobId) {
        List<UserModel> users = userRepository.findUsersByJobId(jobId);
        List<UserJobDetailDTO> userJobDetailDTOS = new ArrayList<>();
        for (UserModel data : users) {
            UserJobDetailDTO userJobDetailDTO = new UserJobDetailDTO();
            userJobDetailDTO.setId(data.getId());
            userJobDetailDTO.setName(data.getFullname());
            List<TaskModel> tasks = taskRepository.findTasksByUserIdAndJobId(jobId, data.getId());
            userJobDetailDTO.setTasks(tasks);
            userJobDetailDTOS.add(userJobDetailDTO);
        }
        return userJobDetailDTOS;
    }

}
