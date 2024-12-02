package com.ckronqvi.website.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ckronqvi.website.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
    Optional<Profile> findByUser_UserId(Long userId);
}
