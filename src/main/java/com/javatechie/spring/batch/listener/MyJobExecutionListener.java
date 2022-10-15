package com.javatechie.spring.batch.listener;

import com.javatechie.spring.batch.entity.JobStatus;
import com.javatechie.spring.batch.repository.JobStatusRepo;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyJobExecutionListener implements JobExecutionListener {
    @Autowired
    private JobStatusRepo jobStatusRepo;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Job " + jobExecution.getStatus());
        JobStatus jobStatus = new JobStatus();

        jobStatus.setId(jobExecution.getJobId());
        jobStatus.setStatus(jobExecution.getStatus().toString());
        jobStatus.setJobName("CSV to DB");
        jobStatus.setStartTime(jobExecution.getStartTime());

        JobStatus result = jobStatusRepo.save(jobStatus);
        System.out.println(result);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Job " + jobExecution.getStatus());

        JobStatus jobStatus = jobStatusRepo.findById(jobExecution.getJobId());
        jobStatus.setEndTime(jobExecution.getEndTime());
        jobStatus.setStatus(jobExecution.getStatus().toString());

        JobStatus result = jobStatusRepo.save(jobStatus);
        System.out.println(result);
    }
}
