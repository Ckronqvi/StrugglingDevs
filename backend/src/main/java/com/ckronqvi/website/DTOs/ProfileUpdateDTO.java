package com.ckronqvi.website.DTOs;

public record ProfileUpdateDTO(
    String full_name,
    String bio,
    String github_url,
    String linkedin_url,
    String portfolio_url,
    String skills
) {
    
}
