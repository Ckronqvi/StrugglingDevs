package com.ckronqvi.website.DTOs;

public record ProjectPostResponseSmallDTO (
    String id,
    String title,
    String recruiter_username,
    String recruiter_companyName,
    String created_at
) {
    
}
