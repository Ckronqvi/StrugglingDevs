package com.ckronqvi.website.DTOMappers;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.ckronqvi.website.DTOs.ProfileDTO;
import com.ckronqvi.website.entities.Profile;

@Service
public class ProfileDTOMapper implements Function<Profile, ProfileDTO> {
        @Override
        public ProfileDTO apply(Profile t) {
            return new ProfileDTO(
                t.getFullName(),
                t.getBio(),
                t.getGithubUrl(),
                t.getLinkedinUrl(),
                t.getPortfolioUrl(),
                t.getSkills()
            );
        }
}
