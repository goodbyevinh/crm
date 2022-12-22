package com.cybersoft.crm.service;

import com.cybersoft.crm.dto.JobDetailDTO;
import com.cybersoft.crm.model.JobModel;
import com.cybersoft.crm.repository.JobRepository;

import java.util.List;

public class JobService implements BaseService<JobModel>{
    JobRepository jobRepository = new JobRepository();

    public static JobService INSTANCE = null;

    public static JobService getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new JobService();
        return INSTANCE;
    }
    @Override
    public List<JobModel> getAllModel() {
        return jobRepository.getAllJobs();
    }

    @Override
    public boolean deleteModelById(int id) {
        return jobRepository.deleteJobById(id) > 0 ? true : false;
    }

    @Override
    public boolean insertModel(JobModel model) {
        return jobRepository.insertJob(model) > 0 ? true : false;
    }

    @Override
    public boolean updateModel(JobModel model) {
        return jobRepository.updateJob(model) > 0 ? true : false;
    }

    public JobModel findJobById(int id) {
        return jobRepository.findJobById(id);
    }

    public JobDetailDTO findProfileJobById(int id) {
        JobModel job = jobRepository.findProfileJobById(id);
        JobDetailDTO jobDetailDTO = new JobDetailDTO();
        if (job.getProfile().getTotalTask() == 0) {
            jobDetailDTO.setYetRate(0);
            jobDetailDTO.setDoingRate(0);
            jobDetailDTO.setCompletedRate(0);
        } else {
            jobDetailDTO.setYetRate(job.getProfile().getYetStartTask() * 100 / job.getProfile().getTotalTask());
            jobDetailDTO.setDoingRate(job.getProfile().getDoingTask() * 100 / job.getProfile().getTotalTask());
            jobDetailDTO.setCompletedRate(job.getProfile().getCompletedTask() * 100 / job.getProfile().getTotalTask());
        }

        return jobDetailDTO;
    }
}
