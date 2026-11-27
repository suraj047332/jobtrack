package com.jobtrack.model;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import java.time.LocalDate; import java.time.LocalDateTime;
@Entity @Table(name="job_applications")
public class JobApplication {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Size(max=120) private String jobTitle;
 @NotBlank @Size(max=120) private String company;
 @Size(max=120) private String location;
 @Size(max=500) private String jobUrl;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private ApplicationStatus status=ApplicationStatus.SAVED;
 private LocalDate appliedDate; private LocalDate interviewDate;
 @Column(length=2000) private String notes;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="user_id") private AppUser user;
 private LocalDateTime createdAt=LocalDateTime.now();
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public String getJobTitle(){return jobTitle;} public void setJobTitle(String v){jobTitle=v;}
 public String getCompany(){return company;} public void setCompany(String v){company=v;}
 public String getLocation(){return location;} public void setLocation(String v){location=v;}
 public String getJobUrl(){return jobUrl;} public void setJobUrl(String v){jobUrl=v;}
 public ApplicationStatus getStatus(){return status;} public void setStatus(ApplicationStatus v){status=v;}
 public LocalDate getAppliedDate(){return appliedDate;} public void setAppliedDate(LocalDate v){appliedDate=v;}
 public LocalDate getInterviewDate(){return interviewDate;} public void setInterviewDate(LocalDate v){interviewDate=v;}
 public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
 public AppUser getUser(){return user;} public void setUser(AppUser v){user=v;}
 public LocalDateTime getCreatedAt(){return createdAt;}
}
