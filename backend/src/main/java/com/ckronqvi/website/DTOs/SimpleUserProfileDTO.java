package com.ckronqvi.website.DTOs;

//What recruiters see
public record SimpleUserProfileDTO (
    String username,
    String full_name,
    String bio,
    String github_url,
    String linkedin_url,
    String portfolio_url,
    String skills
) {

}
