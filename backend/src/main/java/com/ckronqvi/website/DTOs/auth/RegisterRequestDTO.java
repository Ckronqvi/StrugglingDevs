package com.ckronqvi.website.DTOs.auth;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String email;
    
    @Pattern(regexp = "DEVELOPER|RECRUITER", message = "userType must be either 'DEVELOPER' or 'RECRUITER'")
    private String userType;
}