package com.consultancy.services.controller;

import com.consultancy.services.model.JobSeeker;
import com.consultancy.services.service.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/jobseekers")
public class JobSeekerController {

    @Autowired
    private JobSeekerService jobSeekerService;

    @PostMapping
    public ResponseEntity<JobSeeker> createJobSeeker(@ModelAttribute JobSeeker jobSeeker, @RequestParam("resume") MultipartFile resume) throws IOException {
        JobSeeker createdJobSeeker = jobSeekerService.createJobSeeker(jobSeeker, resume);
        return new ResponseEntity<>(createdJobSeeker, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<JobSeeker>> getAllJobSeekers() {
        List<JobSeeker> jobSeekers = jobSeekerService.getAllJobSeekers();
        return new ResponseEntity<>(jobSeekers, HttpStatus.OK);
    }

    @GetMapping("/loadTestData")
    public ResponseEntity<Void> loadJobSeekerTestData() {
        jobSeekerService.loadJobSeekerTestData();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobSeeker> getJobSeekerById(@PathVariable Long id) {
        JobSeeker jobSeeker = jobSeekerService.getJobSeekerById(id);
        if (jobSeeker != null) {
            return new ResponseEntity<>(jobSeeker, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobSeeker> updateJobSeeker(@PathVariable Long id, @RequestBody JobSeeker jobSeekerDetails) {
        JobSeeker updatedJobSeeker = jobSeekerService.updateJobSeeker(id, jobSeekerDetails);
        if (updatedJobSeeker != null) {
            return new ResponseEntity<>(updatedJobSeeker, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobSeeker(@PathVariable Long id) {
        jobSeekerService.deleteJobSeeker(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}