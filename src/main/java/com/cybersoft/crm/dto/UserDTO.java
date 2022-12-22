package com.cybersoft.crm.dto;

import com.cybersoft.crm.model.UserModel;

public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String role;
    private String fullname;

    static public void setFirstNameLastName(UserModel user, UserDTO userDTO) {
        String _fullName = user.getFullname().trim();
        String _firstName = "";
        String _lastName = "";
        if (user.getFullname().trim().contains(" ")) {
            _firstName = _fullName.substring(_fullName.lastIndexOf(' ') + 1);
            _lastName = _fullName.substring(0,_fullName.lastIndexOf(' '));
        } else {
            _firstName = _fullName;
            _lastName = "";
        }
        userDTO.setFirstName(_firstName);
        userDTO.setLastName(_lastName);
    }
    static public void setUserName(UserModel user, UserDTO userDTO) {
        String _userName = "@";
        _userName+= user.getEmail().trim().substring(0, user.getEmail().lastIndexOf('@'));
        userDTO.setUserName(_userName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
