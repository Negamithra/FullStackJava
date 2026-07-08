package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.dto.SystemUserRequestDTO;
import com.example.demo.dto.SystemUserResponseDTO;
import com.example.demo.entity.SystemUser;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.repository.SystemUserRepo;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final SystemUserRepo userRepo;
    private final JwtService jwtService;

    public AuthService(PasswordEncoder passwordEncoder,
                       SystemUserRepo userRepo,
                       JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }

    // REGISTER
    public SystemUserResponseDTO register(SystemUserRequestDTO dto) {

        if (userRepo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        SystemUser user = new SystemUser();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setPhonenumber(dto.getPhonenumber());
        user.setRole(dto.getRole());
        user.setStatus(SystemUser.UserStatus.PENDING);

        SystemUser saved = userRepo.save(user);

        return mapToResponse(saved);
    }

    // LOGIN
    public LoginResponseDTO login(LoginRequestDTO dto) {

        SystemUser user = userRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFound("Invalid Email"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtService.generateToken(user.getEmail());

        LoginResponseDTO response = new LoginResponseDTO();

        response.setMessage("Login Successful");
        response.setToken(token);
        response.setRole(user.getRole().name());
        response.setUserId(user.getId());

        return response;
    }

    // ENTITY -> DTO
    private SystemUserResponseDTO mapToResponse(SystemUser user) {

        SystemUserResponseDTO dto = new SystemUserResponseDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhonenumber(user.getPhonenumber());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());

        return dto;
    }
}