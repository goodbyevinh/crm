package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.JobModel;
import com.cybersoft.crm.model.ProfileModel;
import com.cybersoft.crm.model.RoleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobRepository extends AbstractRepository<JobModel>{
    public List<JobModel> getAllJobs() {
        List<JobModel> jobs = new ArrayList<>();
        String query = "select * from jobs";
        return executeListQuery(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JobModel job = new JobModel();
                job.setId(resultSet.getInt("id"));
                job.setName(resultSet.getString("name"));
                job.setStartDate(resultSet.getString("start_date"));
                job.setEndDate(resultSet.getString("end_date"));
                jobs.add(job);
            }
            return jobs;
        });
    }
    public int insertJob(JobModel job) {
        String query = "insert into jobs(name, start_date, end_date) values (?, ? , ?)";
        return executeUpdate( connection -> {
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(1, job.getName());
           preparedStatement.setString(2, job.getStartDate());
           preparedStatement.setString(3, job.getEndDate());
           return preparedStatement.executeUpdate();
        });
    }
    public int updateJob(JobModel job) {
        String query = "update jobs set name = ?, start_date = ?, end_date = ? where id = ?";
        return executeUpdate( connection -> {
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(1, job.getName());
           preparedStatement.setString(2, job.getStartDate());
           preparedStatement.setString(3, job.getEndDate());
           preparedStatement.setInt(4, job.getId());
           return preparedStatement.executeUpdate();
        });
    }
    public int deleteJobById(int id) {
        String query = "delete from jobs where id = ?";
        return executeUpdate( connection -> {
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setInt(1, id);
           return preparedStatement.executeUpdate();
        });
    }
    public JobModel findJobById(int id) {
        String query = "select * from jobs where id = ?";
        return executeQuery(connection ->  {
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setInt(1, id);
           ResultSet result =  preparedStatement.executeQuery();
           JobModel job = null;
           while (result.next()) {
               job = new JobModel();
               job.setId(result.getInt("id"));
               job.setName(result.getString("name"));
               job.setStartDate(result.getString("start_date"));
               job.setEndDate(result.getString("end_date"));
           }
           return job;
        });
    }
    public JobModel findProfileJobById(int id) {
        String query = "select j.id, count( distinct if(s.id = 1, t.id, null)) as yetStartTask, count( distinct if(s.id = 2, t.id, null)) as doingTask, count( distinct if(s.id = 3, t.id, null)) as completedTask, count(t.id) as totalTask" +
                " from tasks t" +
                " right join jobs j on t.job_id = j.id" +
                " left join status s on t.status_id = s.id" +
                " where j.id = ?" +
                " group by j.id";
        return executeQuery(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            JobModel job = null;
            while (result.next()) {
                job = new JobModel();
                job.setId(result.getInt("id"));
                ProfileModel profile = new ProfileModel();
                profile.setYetStartTask(result.getInt("yetStartTask"));
                profile.setDoingTask(result.getInt("doingTask"));
                profile.setCompletedTask(result.getInt("completedTask"));
                profile.setTotalTask(result.getInt("totalTask"));
                job.setProfile(profile);
            }
            return job;
        });
    }

}
