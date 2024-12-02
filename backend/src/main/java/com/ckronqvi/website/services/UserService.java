package com.ckronqvi.website.services;

import org.springframework.stereotype.Service;

import com.ckronqvi.website.entities.User;
import com.ckronqvi.website.repositories.UserRepository;
import com.ckronqvi.website.DTOMappers.SimpleUserProfileDTOMapper;
import com.ckronqvi.website.DTOMappers.UserProfileDTOMapper;
import com.ckronqvi.website.DTOs.SimpleUserProfileDTO;
import com.ckronqvi.website.DTOs.UserProfileDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProfileService profileService;
    private final SimpleUserProfileDTOMapper simpleUserDTOMapper;
    private final UserProfileDTOMapper userDTOMapper;

    public SimpleUserProfileDTO getUserProfileByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return null;
        }
        return simpleUserDTOMapper.apply(user, profileService.getProfileByUserId(user.getUserId()));
    }

    public UserProfileDTO getCurrentUserProfile(User currentUser) {
        return userDTOMapper.apply(
            userRepository.findById(currentUser.getUserId()).orElse(null),
            profileService.getProfileByUserId(currentUser.getUserId())
        );
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
