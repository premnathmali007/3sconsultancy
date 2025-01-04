package com.consultancy.services.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "job_seekers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobSeeker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String contactNo;

    @Column(nullable = false, unique = true)
    private String email;

    @ElementCollection
    @CollectionTable(name = "job_seeker_skills", joinColumns = @JoinColumn(name = "job_seeker_id"))
    @Column(name = "skill")
    private List<String> skills;

    private Integer noticePeriod;

    private Integer experience;

    private Double currentCtc;

    private Double expectedCtc;

    @Column(name = "resume_url")
    private String resumeUrl;

    @JsonIgnore
    @Transient
    private String resumeFilePath; // Temporary field for file handling

}