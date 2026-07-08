package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.OrganizationRequestDTO;
import com.example.demo.dto.OrganizationResponseDTO;
import com.example.demo.service.OrganizationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/organizations")
@Validated
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<OrganizationResponseDTO> createOrganization(
            @Valid @RequestBody OrganizationRequestDTO dto) {

        return new ResponseEntity<>(
                organizationService.createOrganization(dto),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrganizationResponseDTO>> getAllOrganizations() {

        return ResponseEntity.ok(
                organizationService.getAllOrganizations());

    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponseDTO> getOrganization(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                organizationService.getOrganization(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizationResponseDTO> updateOrganization(
            @PathVariable Long id,
            @Valid @RequestBody OrganizationRequestDTO dto) {

        return ResponseEntity.ok(
                organizationService.updateOrganization(id, dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrganization(
            @PathVariable Long id) {

        organizationService.deleteOrganization(id);

        return ResponseEntity.ok("Organization deleted successfully");

    }

}