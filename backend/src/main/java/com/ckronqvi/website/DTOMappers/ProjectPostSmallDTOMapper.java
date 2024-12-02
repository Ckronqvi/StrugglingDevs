package com.ckronqvi.website.DTOMappers;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.ckronqvi.website.DTOs.ProjectPostResponseSmallDTO;
import com.ckronqvi.website.entities.ProjectPost;

@Service
public class ProjectPostSmallDTOMapper implements Function<ProjectPost, ProjectPostResponseSmallDTO>{

    @Override
    public ProjectPostResponseSmallDTO apply(ProjectPost t) {
        return new ProjectPostResponseSmallDTO(
            t.getPostId().toString(),
            t.getTitle(),
            t.getRecruiter().getUsername(),
            t.getRecruiter().getCompanyName(),
            t.getCreatedAt().toString()
        );
    }
    
}
