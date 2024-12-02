package com.ckronqvi.website.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ckronqvi.website.Annotations.CurrentUser;
import com.ckronqvi.website.DTOs.ProfileDTO;
import com.ckronqvi.website.DTOMappers.ProfileDTOMapper;
import com.ckronqvi.website.DTOs.ProfileUpdateDTO;
import com.ckronqvi.website.DTOs.SimpleUserProfileDTO;
import com.ckronqvi.website.DTOs.UserProfileDTO;
import com.ckronqvi.website.entities.User;
import com.ckronqvi.website.services.ProfileService;
import com.ckronqvi.website.services.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserProfileController {

    private final UserService userService;
    private final ProfileService profileService;
    private final ProfileDTOMapper profileDTOMapper;

    @GetMapping("/{username}")
    public ResponseEntity<SimpleUserProfileDTO> getUserProfileByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserProfileByUsername(username));
    }

    @GetMapping
    public ResponseEntity<UserProfileDTO> getCurrentUserProfile(@CurrentUser User currentUser) {
        return ResponseEntity.ok(userService.getCurrentUserProfile(currentUser));
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> updateUserProfile(
            @CurrentUser User currentUser,
            @RequestBody ProfileUpdateDTO profileUpdateDTO) {
        return ResponseEntity.ok(
                profileDTOMapper.apply(
                        profileService.updateProfile(currentUser.getUserId(), profileUpdateDTO)));
    }
}
