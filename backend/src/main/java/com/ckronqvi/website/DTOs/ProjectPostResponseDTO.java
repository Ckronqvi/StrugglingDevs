package com.ckronqvi.website.DTOs;

public record ProjectPostResponseDTO (
    String id,
    String title,
    String description,
    String recruiter_username,
    String recruiter_companyName,
    String created_at
){
    
}
