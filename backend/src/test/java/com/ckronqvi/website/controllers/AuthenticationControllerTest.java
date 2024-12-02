package com.ckronqvi.website.controllers;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.ckronqvi.website.DTOs.auth.AuthenticationRequestDTO;
import com.ckronqvi.website.DTOs.auth.AuthenticationResponseDTO;
import com.ckronqvi.website.DTOs.auth.RegisterRequestDTO;

import com.ckronqvi.website.services.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;



@SpringBootTest
@AutoConfigureMockMvc   
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean  
    private AuthenticationService authenticationService;

    @SuppressWarnings("unused")
    @Autowired 
    private AuthenticationController authenticationController;

    @Autowired
    private ObjectMapper objectMapper;  

    @BeforeEach
    void setUp() {
        
    }

    @Test
    void testRegisterSuccess() throws Exception {
        // Create proper test data
        RegisterRequestDTO request = RegisterRequestDTO.builder()
            .email("test@example.com")
            .password("password123")
            .username("testuser")
            .build();

        AuthenticationResponseDTO response = AuthenticationResponseDTO.builder()
            .token("test-jwt-token")
            .build();

        when(authenticationService.register(any(RegisterRequestDTO.class)))
            .thenReturn(response);

        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("test-jwt-token"));

    }

    @Test
    void testRegisterBadRequest() throws Exception {
        RegisterRequestDTO request = new RegisterRequestDTO(); // empty request

        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAuthenticateSuccess() throws Exception {
        AuthenticationRequestDTO request = AuthenticationRequestDTO.builder()
            .username("testuser")
            .password("password123")
            .build();

        AuthenticationResponseDTO response = AuthenticationResponseDTO.builder()
            .token("test-jwt-token")
            .build();

        when(authenticationService.authenticate(any(AuthenticationRequestDTO.class)))
            .thenReturn(response);

        mockMvc.perform(post("/api/v1/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("test-jwt-token"));
    }

    @Test
    void testAuthenticateFailure() throws Exception {
        AuthenticationRequestDTO request = new AuthenticationRequestDTO(); // empty request

        when(authenticationService.authenticate(any(AuthenticationRequestDTO.class)))
            .thenThrow(new BadCredentialsException("Invalid credentials"));

        mockMvc.perform(post("/api/v1/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}