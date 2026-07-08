package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.SystemUserRequestDTO;
import com.example.demo.dto.SystemUserResponseDTO;
import com.example.demo.service.SystemUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class SystemUserController {

    private final SystemUserService systemUserService;

    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    // Create User
    @PostMapping
    public ResponseEntity<SystemUserResponseDTO> createUser(
            @Valid @RequestBody SystemUserRequestDTO dto) {

        SystemUserResponseDTO response = systemUserService.createUser(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<SystemUserResponseDTO>> getAllUsers() {

        return ResponseEntity.ok(systemUserService.getAllUsers());
    }

    // Get User By ID
    @GetMapping("/{id}")
    public ResponseEntity<SystemUserResponseDTO> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(systemUserService.getUserById(id));
    }

    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<SystemUserResponseDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody SystemUserRequestDTO dto) {

        return ResponseEntity.ok(systemUserService.updateUser(id, dto));
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        systemUserService.deleteUser(id);

        return ResponseEntity.ok("User deleted successfully.");
    }
}