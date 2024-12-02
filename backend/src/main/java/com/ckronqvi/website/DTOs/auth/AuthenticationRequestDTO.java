package com.ckronqvi.website.DTOs.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequestDTO {
    @NotBlank @NotNull
    private String username;
    @NotBlank @NotNull
    private String password;
}