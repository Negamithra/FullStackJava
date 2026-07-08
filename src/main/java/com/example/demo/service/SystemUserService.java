package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SystemUserRequestDTO;
import com.example.demo.dto.SystemUserResponseDTO;
import com.example.demo.entity.SystemUser;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.repository.SystemUserRepo;

@Service
public class SystemUserService {

    private final SystemUserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public SystemUserService(SystemUserRepo userRepo,
                             PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // CREATE
    public SystemUserResponseDTO createUser(SystemUserRequestDTO dto) {

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

    // GET ALL
    public List<SystemUserResponseDTO> getAllUsers() {

        return userRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public SystemUserResponseDTO getUserById(Long id) {

        SystemUser user = userRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFound("User not found"));

        return mapToResponse(user);
    }

    // UPDATE
    public SystemUserResponseDTO updateUser(Long id,
                                            SystemUserRequestDTO dto) {

        SystemUser user = userRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFound("User not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhonenumber(dto.getPhonenumber());
        user.setRole(dto.getRole());
       // user.setStatus(dto.getStatus());

        if (dto.getPassword() != null &&
            !dto.getPassword().isBlank()) {

            user.setPassword(
                    passwordEncoder.encode(dto.getPassword()));
        }

        SystemUser updated = userRepo.save(user);

        return mapToResponse(updated);
    }

    // DELETE
    public void deleteUser(Long id) {

        SystemUser user = userRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFound("User not found"));

        userRepo.delete(user);
    }

    // ENTITY -> DTO
    private SystemUserResponseDTO mapToResponse(SystemUser user) {

        SystemUserResponseDTO dto =
                new SystemUserResponseDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhonenumber(user.getPhonenumber());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());

        return dto;
    }
}