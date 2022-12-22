package com.cybersoft.crm.service;

import com.cybersoft.crm.model.TaskModel;
import com.cybersoft.crm.repository.TaskRepository;

import java.util.List;

public class TaskService implements BaseService<TaskModel>{
    TaskRepository taskRepository = new TaskRepository();

    public static TaskService INSTANCE = null;

    public static TaskService getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new TaskService();
        return INSTANCE;
    }

    @Override
    public List<TaskModel> getAllModel() {
        return taskRepository.getAllTasks();
    }

    @Override
    public boolean deleteModelById(int id) {
        return taskRepository.deleteTaskById(id) > 0 ? true : false;
    }

    @Override
    public boolean insertModel(TaskModel model) {
        return taskRepository.insertTask(model) > 0 ? true : false;
    }

    @Override
    public boolean updateModel(TaskModel model) {
        return taskRepository.updateTask(model) > 0 ? true : false;
    }

    public List<TaskModel> findTasksById(int id) {
        return taskRepository.findTasksByIdUser(id);
    }
    public TaskModel findTaskById(int id) {
        return taskRepository.findTaskById(id);
    }
    public boolean updateStatusTask(int taskId, int statusId) {
        return taskRepository.updateStatusTask(taskId, statusId) > 0 ? true : false;
    }
}
