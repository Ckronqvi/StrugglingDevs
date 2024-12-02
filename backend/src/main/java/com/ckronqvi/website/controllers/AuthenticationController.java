package com.ckronqvi.website.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ckronqvi.website.DTOs.auth.AuthenticationRequestDTO;
import com.ckronqvi.website.DTOs.auth.AuthenticationResponseDTO;
import com.ckronqvi.website.DTOs.auth.RegisterRequestDTO;
import com.ckronqvi.website.services.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO request
    ) {
        AuthenticationResponseDTO response = authenticationService.register(request);
        if (response == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}