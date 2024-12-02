package com.ckronqvi.website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ckronqvi.website.entities.ProjectInterest;

@Repository
public interface ProjectInterestRepository extends JpaRepository<ProjectInterest, Long> {
    @Query("""
        SELECT CASE WHEN COUNT(pi) > 0 THEN true ELSE false END
        FROM ProjectInterest pi
        WHERE pi.developer.userId = :developerId
        AND pi.projectPost.recruiter.userId = :recruiterId
        AND pi.status = :status
    """)
    boolean existsByDeveloperIdAndRecruiterIdAndStatus(
        Long developerId, 
        Long recruiterId, 
        String status
    );
}