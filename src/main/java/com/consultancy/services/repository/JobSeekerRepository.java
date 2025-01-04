package com.consultancy.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consultancy.services.model.JobSeeker; 

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {
    // You can add custom query methods here if needed
    // Example:
    // List<JobSeeker> findByFirstName(String firstName); 
}