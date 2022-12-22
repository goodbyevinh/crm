package com.cybersoft.crm.dto;

import com.cybersoft.crm.model.TaskModel;

import java.util.List;

public class UserJobDetailDTO {
    private int id;
    private String name;
    private List<TaskModel> tasks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskModel> tasks) {
        this.tasks = tasks;
    }
}
