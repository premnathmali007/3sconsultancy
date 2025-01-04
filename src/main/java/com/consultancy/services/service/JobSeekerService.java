package com.consultancy.services.service;

import com.consultancy.services.model.JobSeeker;
import com.consultancy.services.repository.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class JobSeekerService {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    public JobSeeker createJobSeeker(JobSeeker jobSeeker, MultipartFile resume) throws IOException {
        String uploadDir = "uploads/"; // Define upload directory
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = resume.getOriginalFilename();
        String filePath = uploadDir + fileName;
        jobSeeker.setResumeFilePath(filePath);

        Files.copy(resume.getInputStream(), Paths.get(filePath));

        jobSeeker.setResumeUrl("/uploads/" + fileName); // Set URL for accessing the file

        return jobSeekerRepository.save(jobSeeker);
    }

    public List<JobSeeker> getAllJobSeekers() {
        return jobSeekerRepository.findAll();
    }

    public JobSeeker getJobSeekerById(Long id) {
        return jobSeekerRepository.findById(id).orElse(null);
    }

    public JobSeeker updateJobSeeker(Long id, JobSeeker jobSeekerDetails) {
        Optional<JobSeeker> jobSeeker = jobSeekerRepository.findById(id);
        if (jobSeeker.isPresent()) {
            JobSeeker existingJobSeeker = jobSeeker.get();
            existingJobSeeker.setFirstName(jobSeekerDetails.getFirstName());
            existingJobSeeker.setLastName(jobSeekerDetails.getLastName());
            existingJobSeeker.setContactNo(jobSeekerDetails.getContactNo());
            existingJobSeeker.setEmail(jobSeekerDetails.getEmail());
            existingJobSeeker.setSkills(jobSeekerDetails.getSkills());
            existingJobSeeker.setNoticePeriod(jobSeekerDetails.getNoticePeriod());
            existingJobSeeker.setExperience(jobSeekerDetails.getExperience());
            existingJobSeeker.setCurrentCtc(jobSeekerDetails.getCurrentCtc());
            existingJobSeeker.setExpectedCtc(jobSeekerDetails.getExpectedCtc());
            return jobSeekerRepository.save(existingJobSeeker);
        } else {
            return null;
        }
    }

    public void deleteJobSeeker(Long id) {
        jobSeekerRepository.deleteById(id);
    }
}