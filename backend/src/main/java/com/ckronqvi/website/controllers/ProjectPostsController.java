package com.ckronqvi.website.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ckronqvi.website.Annotations.CurrentUser;
import com.ckronqvi.website.DTOs.ProjectPostRequestDTO;
import com.ckronqvi.website.DTOs.ProjectPostResponseDTO;
import com.ckronqvi.website.DTOs.ProjectPostResponseSmallDTO;
import com.ckronqvi.website.entities.User;
import com.ckronqvi.website.services.ProjectPostsService;

@RestController
@RequestMapping("/api/v1/project-posts")
public class ProjectPostsController {

    private final ProjectPostsService projectPostsService;

    public ProjectPostsController(ProjectPostsService projectPostsService) {
        this.projectPostsService = projectPostsService;
    }

    @GetMapping()
    public ResponseEntity<List<ProjectPostResponseSmallDTO>> getProjectPosts() {
        return ResponseEntity.ok(projectPostsService.getProjectPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectPostResponseDTO> getProjectPostById(@PathVariable("id") Long id) {
        ProjectPostResponseDTO projectPost = projectPostsService.getProjectPostById(id);
        return ResponseEntity.ok(projectPost);
    }

    @PostMapping
    public ResponseEntity<ProjectPostResponseDTO> createProjectPost(@CurrentUser User user,
            @RequestBody ProjectPostRequestDTO projectPostCreationDTO) {
        ProjectPostResponseDTO createdProjectPost = projectPostsService.createProjectPost(user, projectPostCreationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProjectPost);
    }

}