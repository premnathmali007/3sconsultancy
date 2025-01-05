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
import java.util.ArrayList;
import java.util.Arrays;
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

    public void loadJobSeekerTestData() {
        List<JobSeeker> jobSeekers = new ArrayList<>();

        String[] firstNames = {"Aakash", "Avani", "Rohan", "Riya", "Aditya", "Aisha", "Vivek", "Priya", "Siddharth", "Isha"};
        String[] lastNames = {"Sharma", "Kumar", "Patel", "Singh", "Verma", "Gupta", "Jain", "Khanna", "Rao", "Roy"};
        String[] skills = {"Java", "Python", "JavaScript", "php", "C#", "DotNet", "React", "Data Science", "Spring boot", "DevOps"};

        for (int i = 0; i < 10; i++) {
            int randomFirstNameIndex = (int) (Math.random() * firstNames.length);
            int randomLastNameIndex = (int) (Math.random() * lastNames.length);
            List<String> randomSkills = new ArrayList<>();
            for (int j = 0; j < (int) (Math.random() * skills.length + 1); j++) { // Add 0 to 'skills.length' random skills
                int randomSkillIndex = (int) (Math.random() * skills.length);
                randomSkills.add(skills[randomSkillIndex]);
            }

            jobSeekers.add(JobSeeker.builder()
                    .firstName(firstNames[randomFirstNameIndex])
                    .lastName(lastNames[randomLastNameIndex])
                    .contactNo("9" + String.format("%09d", (int) (Math.random() * 1000000000))) // Generate random 10-digit number starting with 9
                    .email(firstNames[randomFirstNameIndex].toLowerCase() + "." + lastNames[randomLastNameIndex].toLowerCase() + "@example.com")
                    .skills(randomSkills)
                    .noticePeriod(30 + (int) (Math.random() * 30)) // Random notice period between 30 and 59 days
                    .experience((int) (Math.random() * 10 + 1)) // Random experience between 1 and 10 years
                    .currentCtc(50000.0 + (Math.random() * 100000.0)) // Random current CTC between 500000 and 600000
                    .expectedCtc(70000.0 + (Math.random() * 150000.0)) // Random expected CTC between 700000 and 850000
                    .build());
        }
        jobSeekerRepository.saveAll(jobSeekers);
    }
}