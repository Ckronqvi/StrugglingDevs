package com.ckronqvi.website.services;

import org.springframework.stereotype.Service;

import com.ckronqvi.website.DTOs.auth.AuthenticationRequestDTO;
import com.ckronqvi.website.DTOs.auth.AuthenticationResponseDTO;
import com.ckronqvi.website.DTOs.auth.RegisterRequestDTO;
import com.ckronqvi.website.entities.Profile;
import com.ckronqvi.website.entities.Role;
import com.ckronqvi.website.entities.User;
import com.ckronqvi.website.enums.RoleName;
import com.ckronqvi.website.enums.UserType;
import com.ckronqvi.website.repositories.ProfileRepository;
import com.ckronqvi.website.repositories.RoleRepository;
import com.ckronqvi.website.repositories.UserRepository;
import com.ckronqvi.website.security.JwtService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ckronqvi.website.exceptions.UsernameOrEmailAlreadyExistException;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {
  private final UserRepository userRepository;
  private final ProfileRepository profileRepository;
  private final RoleRepository roleRepository;

  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponseDTO register(RegisterRequestDTO request) {
    if(userRepository.findByUsername(request.getUsername()).isPresent() || userRepository.findByEmail(request.getEmail()).isPresent()) {
        throw new UsernameOrEmailAlreadyExistException("Username or Email already exists");
    }
      // Create User
      UserType userType = UserType.valueOf(request.getUserType());
      var user = User.builder()
          .username(request.getUsername())
          .password(passwordEncoder.encode(request.getPassword()))
          .email(request.getEmail())
          .userType(userType)
          .roles(createRoles(RoleName.ROLE_USER))
          .build();

      user = userRepository.save(user);

      // Create Profile
      var profile = Profile.builder()
          .user(user)
          .build();

      profileRepository.save(profile);

      var jwtToken = jwtService.generateToken(user);
      return AuthenticationResponseDTO.builder()
          .token(jwtToken)
          .build();
}

  public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()));
    var user = userRepository.findByUsername(request.getUsername())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponseDTO.builder()
        .token(jwtToken)
        .build();
  }

  private Set<Role> createRoles(RoleName... roleNames) {
    Set<Role> roles = new HashSet<>();
    for (RoleName roleName : roleNames) {
      roles.add(roleRepository.findByName(roleName)
          .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)));
    }
    return roles;
  }
}