package com.ckronqvi.website.DTOMappers;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.ckronqvi.website.DTOs.ProjectPostResponseDTO;
import com.ckronqvi.website.entities.ProjectPost;

@Service
public class ProjectPostResponseDTOMapper implements Function<ProjectPost, ProjectPostResponseDTO> {

    @Override
    public ProjectPostResponseDTO apply(ProjectPost t) {
        return new ProjectPostResponseDTO(
                t.getPostId().toString(),
                t.getTitle(),
                t.getDescription(),
                t.getRecruiter().getUsername(),
                t.getRecruiter().getCompanyName(),
                t.getCreatedAt().toString());
    }

}
