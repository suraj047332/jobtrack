package com.jobtrack.repository;
import com.jobtrack.model.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface JobApplicationRepository extends JpaRepository<JobApplication,Long>{
 List<JobApplication> findAllByUser_IdOrderByCreatedAtDesc(Long userId);
 Optional<JobApplication> findByIdAndUser_Id(Long id,Long userId);
 long countByUser_Id(Long userId); long countByUser_IdAndStatus(Long userId,ApplicationStatus status);
 List<JobApplication> findTop5ByUser_IdOrderByCreatedAtDesc(Long userId);
}
