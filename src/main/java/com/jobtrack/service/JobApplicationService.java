package com.jobtrack.service;
import com.jobtrack.model.*; import com.jobtrack.repository.JobApplicationRepository; import org.springframework.stereotype.Service; import java.util.*;
@Service public class JobApplicationService {
 private final JobApplicationRepository repo; private final UserService users;
 public JobApplicationService(JobApplicationRepository repo,UserService users){this.repo=repo;this.users=users;}
 public List<JobApplication> all(String email){return repo.findAllByUser_IdOrderByCreatedAtDesc(users.byEmail(email).getId());}
 public JobApplication get(Long id,String email){return repo.findByIdAndUser_Id(id,users.byEmail(email).getId()).orElseThrow(()->new NoSuchElementException("Application not found"));}
 public JobApplication save(JobApplication app,String email){app.setUser(users.byEmail(email));return repo.save(app);}
 public void delete(Long id,String email){repo.delete(get(id,email));}
 public Map<String,Long> stats(String email){Long uid=users.byEmail(email).getId();Map<String,Long> m=new LinkedHashMap<>();m.put("total",repo.countByUser_Id(uid));for(ApplicationStatus s:ApplicationStatus.values())m.put(s.name().toLowerCase(),repo.countByUser_IdAndStatus(uid,s));return m;}
 public List<JobApplication> recent(String email){return repo.findTop5ByUser_IdOrderByCreatedAtDesc(users.byEmail(email).getId());}
}
