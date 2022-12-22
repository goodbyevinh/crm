package com.cybersoft.crm.utils;

public enum PathEnum {
    NOT_FOUND("/404.html"),
    BLANK("/blank.html"),
    JOB("/groupwork.jsp"),
    ADD_JOB("/groupwork-add.jsp"),
    UPDATE_JOB("/groupwork-update.jsp"),
    JOB_DETAIL("/groupwork-details.jsp"),
    INDEX("/index.html"),
    LOGIN("/login.html"),
    PROFILE("/profile.jsp"),
    PROFILE_EDIT("/profile-edit.jsp"),
    ROLE("/role-table.jsp"),
    ADD_ROLE("/role-add.jsp"),
    UPDATE_ROLE("/role-update.jsp"),
    TASK("/task.jsp"),
    ADD_TASK("/task-add.jsp"),
    UPDATE_TASK("/task-update.jsp"),
    USER("/user-table.jsp"),
    ADD_USER("/user-add.jsp"),
    UPDATE_USER("/user-update.jsp"),
    USER_DETAIL("/user-details.jsp");

    private String path;
    PathEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
