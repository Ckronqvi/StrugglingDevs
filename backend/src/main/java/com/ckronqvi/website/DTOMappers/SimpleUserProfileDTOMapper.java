package com.ckronqvi.website.DTOMappers;

import org.springframework.stereotype.Service;

import com.ckronqvi.website.DTOs.SimpleUserProfileDTO;
import com.ckronqvi.website.entities.Profile;
import com.ckronqvi.website.entities.User;

@Service
public class SimpleUserProfileDTOMapper {
    
    public SimpleUserProfileDTO apply(User user, Profile profile) {
        return new SimpleUserProfileDTO(
            user.getUsername(),
            profile.getFullName(),
            profile.getBio(),
            profile.getGithubUrl(),
            profile.getLinkedinUrl(),
            profile.getPortfolioUrl(),
            profile.getSkills()
        );  
    }
}