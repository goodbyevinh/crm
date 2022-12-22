package com.cybersoft.crm.service;

import com.cybersoft.crm.model.UserModel;
import com.cybersoft.crm.repository.UserRepository;

import java.util.List;

public class LoginService {

    UserRepository userRepository = new UserRepository();
    public boolean checkLogin(String email, String password) {
        List<UserModel> userModelList = userRepository.getUsersByEmailAndPassword(email, password);

        if (userModelList.size() > 0) {
            // Đăng nhập thành công
            return true;
        } else {
            // Đăng nhập thất bại
            return false;
        }

    }
}
