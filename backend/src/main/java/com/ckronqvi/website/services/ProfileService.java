package com.ckronqvi.website.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ckronqvi.website.DTOs.ProfileUpdateDTO;
import com.ckronqvi.website.entities.Profile;
import com.ckronqvi.website.exceptions.ResourceNotFoundException;
import com.ckronqvi.website.repositories.ProfileRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    //get profile by user id
    public Profile getProfileByUserId(Long userId) {
        return profileRepository.findByUser_UserId(userId).orElse(null);
    }

    public Profile updateProfile(Long profileId, ProfileUpdateDTO updates) {
        Profile existingProfile = profileRepository.findById(profileId)
            .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        existingProfile.setFullName(updates.full_name() != null ? updates.full_name() : existingProfile.getFullName());
        existingProfile.setBio(updates.bio() != null ? updates.bio() : existingProfile.getBio());
        existingProfile.setGithubUrl(updates.github_url() != null ? updates.github_url() : existingProfile.getGithubUrl());
        existingProfile.setLinkedinUrl(updates.linkedin_url() != null ? updates.linkedin_url() : existingProfile.getLinkedinUrl());
        existingProfile.setPortfolioUrl(updates.portfolio_url() != null ? updates.portfolio_url() : existingProfile.getPortfolioUrl());
        existingProfile.setSkills(updates.skills() != null ? updates.skills() : existingProfile.getSkills());
        existingProfile.setUpdatedAt(LocalDateTime.now());
        return profileRepository.save(existingProfile);
    }
}
