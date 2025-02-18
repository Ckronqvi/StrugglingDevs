package com.ckronqvi.website.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ckronqvi.website.DTOs.ProjectPostRequestDTO;
import com.ckronqvi.website.DTOs.ProjectPostResponseDTO;
import com.ckronqvi.website.DTOs.ProjectPostResponseSmallDTO;
import com.ckronqvi.website.entities.ProjectPost;
import com.ckronqvi.website.entities.User;
import com.ckronqvi.website.repositories.ProjectPostsRepository;
import com.ckronqvi.website.DTOMappers.ProjectPostResponseDTOMapper;
import com.ckronqvi.website.DTOMappers.ProjectPostSmallDTOMapper;
import com.ckronqvi.website.enums.ProjectStatus;
import com.ckronqvi.website.exceptions.ResourceNotFoundException;
import com.ckronqvi.website.exceptions.UnauthorizedException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProjectPostsService {
    private final ProjectPostsRepository projectPostsRepository;
    private final ProjectPostResponseDTOMapper projectPostResponseDTOMapper;

    public List<ProjectPostResponseSmallDTO> getProjectPosts() {
        return projectPostsRepository.findAll().stream()
                .map(new ProjectPostSmallDTOMapper())
                .collect(Collectors.toList());
    }

    public ProjectPostResponseDTO getProjectPostById(Long id) {
        return projectPostsRepository.findById(id)
                .map(projectPostResponseDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException("Project post not found"));
    }

    public ProjectPostResponseDTO createProjectPost(User user, ProjectPostRequestDTO projectPostCreationDTO) {
        ProjectPost projectPost = new ProjectPost();
        projectPost.setRecruiter(user);
        projectPost.setTitle(projectPostCreationDTO.title());
        projectPost.setDescription(projectPostCreationDTO.description());
        projectPost.setStatus(ProjectStatus.OPEN);
        projectPost.setCreatedAt(LocalDateTime.now());
        projectPost.setUpdatedAt(LocalDateTime.now());
        return projectPostResponseDTOMapper.apply(projectPostsRepository.save(projectPost));
    }

    public void deleteProjectPost(User user, Long id) {
        ProjectPost projectPost = projectPostsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project post not found"));
        if (!projectPost.getRecruiter().equals(user)) {
            throw new UnauthorizedException("You are not allowed to delete this project post");
        }
        projectPostsRepository.delete(projectPost);
    }

    public void updateProjectPost(User user, Long id, ProjectPostRequestDTO projectPostUpdateDTO) {
        ProjectPost projectPost = projectPostsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project post not found"));
        if (!projectPost.getRecruiter().equals(user)) {
            throw new UnauthorizedException("You are not allowed to update this project post");
        }
        projectPost.setTitle(projectPostUpdateDTO.title());
        projectPost.setDescription(projectPostUpdateDTO.description());
        projectPost.setUpdatedAt(LocalDateTime.now());
        projectPostsRepository.save(projectPost);
    }
}
