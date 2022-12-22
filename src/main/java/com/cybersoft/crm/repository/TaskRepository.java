package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository extends AbstractRepository<TaskModel> {
    public List<TaskModel> getAllTasks() {
        List<TaskModel> tasks = new ArrayList<>();
        String query = "select t.id, t.name as taskName, j.name as jobName, u.fullname as userName, t.start_date, t.end_date, s.name as status from tasks t join jobs j on t.job_id = j.id join users u on t.user_id = u.id join status s on t.status_id = s.id";
        return executeListQuery(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TaskModel task = new TaskModel();
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("taskName"));
                task.setEndDate(resultSet.getString("end_date"));
                task.setStartDate(resultSet.getString("start_date"));

                StatusModel status = new StatusModel();
                status.setName(resultSet.getString("status"));
                task.setStatus(status);

                JobModel job = new JobModel();
                job.setName(resultSet.getString("jobName"));
                task.setJob(job);

                UserModel user = new UserModel();
                user.setFullname(resultSet.getString("userName"));
                user.setFullname(user.getFullname());
                task.setUser(user);

                tasks.add(task);
            }
            return tasks;
        });
    }
    public int deleteTaskById(int id) {
        String query = "delete from tasks t where t.id = ?";
        return executeUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        });
    }

    public int insertTask(TaskModel task) {
        String query = "insert into tasks(name, start_date, end_date, job_id, user_id, status_id) values(?, ? , ?, ?, ?, ?)";

        return executeUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getStartDate());
            preparedStatement.setString(3, task.getEndDate());
            preparedStatement.setInt(4, task.getJob().getId());
            preparedStatement.setInt(5, task.getUser().getId());
            preparedStatement.setInt(6, 1);

            return preparedStatement.executeUpdate();
        });
    }

    public int updateTask(TaskModel task) {
        String query = "update tasks set name = ?, start_date = ?, end_date = ?, job_id = ?, user_id = ? where id = ?;";

        return executeUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getStartDate());
            preparedStatement.setString(3, task.getEndDate());
            preparedStatement.setInt(4, task.getJob().getId());
            preparedStatement.setInt(5, task.getUser().getId());
            preparedStatement.setInt(6, task.getId());
            return preparedStatement.executeUpdate();
        });
    }
    public List<TaskModel> findTasksByIdUser(int userId) {
        List<TaskModel> tasks = new ArrayList<>();
        String query = "select t.id, t.name as taskName, j.name as jobName, t.start_date, t.end_date, s.name as status, s.id as statusId from tasks t join jobs j on t.job_id = j.id join users u on t.user_id = u.id join status s on t.status_id = s.id where u.id = ?";

        return executeListQuery(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TaskModel task = new TaskModel();
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("taskName"));
                task.setEndDate(resultSet.getString("end_date"));
                task.setStartDate(resultSet.getString("start_date"));

                StatusModel status = new StatusModel();
                status.setId(resultSet.getInt("statusId"));
                status.setName(resultSet.getString("status"));
                task.setStatus(status);

                JobModel job = new JobModel();
                job.setName(resultSet.getString("jobName"));
                task.setJob(job);


                tasks.add(task);
            }
            return tasks;
        });
    }

    public int updateStatusTask(int taskId, int statusId) {
        String query = "update tasks set status_id = ? where id = ?;";
        return executeUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, statusId);
            preparedStatement.setInt(2, taskId);
            return preparedStatement.executeUpdate();
        });
    }
    public TaskModel findTaskById(int id) {
        String query = "select t.id as id, t.name as taskName, j.name as jobName, t.start_date, t.end_date, s.name as statusName, u.id as userId, j.id as jobId" +
                " from tasks t" +
                " join jobs j on t.job_id = j.id" +
                " join status s on t.status_id = s.id" +
                " join users u on t.user_id = u.id" +
                " where t.id = ? ";
        return executeQuery(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            TaskModel task = null;
            while (result.next()) {
                StatusModel status = new StatusModel();
                JobModel job = new JobModel();
                task = new TaskModel();
                status.setName(result.getString("statusName"));
                job.setName(result.getString("jobName"));
                job.setId(result.getInt("jobId"));
                task.setName(result.getString("taskName"));
                task.setJob(job);
                task.setStatus(status);
                task.setStartDate(result.getString("start_date"));
                task.setEndDate(result.getString("end_date"));
                task.setId(result.getInt("id"));
                UserModel user = new UserModel();
                user.setId(result.getInt("userId"));
                task.setUser(user);
            }
            return task;
        });
    }
    public List<TaskModel> findTasksByUserIdAndJobId(int jobId, int userId) {
        String query = "select t.name, t.start_date, t.end_date, s.id as statusId" +
                " from tasks t" +
                " join status s on t.status_id = s.id" +
                " where t.user_id = ? and t.job_id = ?";
        List<TaskModel> tasks = new ArrayList<>();
        return executeListQuery(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, jobId);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                TaskModel task = new TaskModel();
                task.setName(result.getString("name"));
                task.setStartDate(result.getString("start_date"));
                task.setEndDate(result.getString("end_date"));
                StatusModel status = new StatusModel();
                status.setId(result.getInt("statusId"));
                task.setStatus(status);
                tasks.add(task);
            }
            return tasks;
        });
    }
}
