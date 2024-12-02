package com.ckronqvi.website.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String fullName;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private String githubUrl;
    private String linkedinUrl;
    private String portfolioUrl;

    @Column(columnDefinition = "TEXT")
    private String skills;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
