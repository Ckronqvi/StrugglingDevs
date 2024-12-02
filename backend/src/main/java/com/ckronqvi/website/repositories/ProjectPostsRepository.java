package com.ckronqvi.website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ckronqvi.website.entities.ProjectPost;

public interface ProjectPostsRepository extends JpaRepository <ProjectPost, Long>{
    
}
