package com.javatechie.spring.batch.repository;

import com.javatechie.spring.batch.entity.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobStatusRepo extends JpaRepository<JobStatus,Integer> {
    JobStatus findById(Long jobId);
}
