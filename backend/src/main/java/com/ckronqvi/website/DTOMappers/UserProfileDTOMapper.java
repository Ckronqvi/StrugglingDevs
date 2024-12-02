package com.ckronqvi.website.DTOMappers;

import org.springframework.stereotype.Service;

import com.ckronqvi.website.DTOs.UserProfileDTO;
import com.ckronqvi.website.entities.Profile;
import com.ckronqvi.website.entities.User;

@Service
public class UserProfileDTOMapper {
    
    public UserProfileDTO apply(User user, Profile profile) {
        return new UserProfileDTO(
            user.getUsername(),
            user.getEmail(),
            user.getUserType().name(),
            profile.getFullName(),
            profile.getBio(),
            profile.getGithubUrl(),
            profile.getLinkedinUrl(),
            profile.getPortfolioUrl(),
            profile.getSkills()
        );  
    }
}