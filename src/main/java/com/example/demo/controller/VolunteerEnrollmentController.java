package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.VolunteerEnrollmentRequestDTO;
import com.example.demo.dto.VolunteerEnrollmentResponseDTO;
import com.example.demo.service.VolunteerEnrollmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/volunteer-enrollments")
public class VolunteerEnrollmentController {

    private final VolunteerEnrollmentService enrollmentService;

    public VolunteerEnrollmentController(VolunteerEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // Create Enrollment
    @PostMapping
    public ResponseEntity<VolunteerEnrollmentResponseDTO> createEnrollment(
            @Valid @RequestBody VolunteerEnrollmentRequestDTO dto) {

        VolunteerEnrollmentResponseDTO response = enrollmentService.createEnrollment(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get All Enrollments
    @GetMapping
    public ResponseEntity<List<VolunteerEnrollmentResponseDTO>> getAllEnrollments() {

        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    // Get Enrollment By ID
    @GetMapping("/{id}")
    public ResponseEntity<VolunteerEnrollmentResponseDTO> getEnrollmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(enrollmentService.getEnrollmentById(id));
    }

    // Update Enrollment
    @PutMapping("/{id}")
    public ResponseEntity<VolunteerEnrollmentResponseDTO> updateEnrollment(
            @PathVariable Long id,
            @Valid @RequestBody VolunteerEnrollmentRequestDTO dto) {

        VolunteerEnrollmentResponseDTO response =
                enrollmentService.updateEnrollment(id, dto);

        return ResponseEntity.ok(response);
    }

    // Delete Enrollment
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEnrollment(
            @PathVariable Long id) {

        enrollmentService.deleteEnrollment(id);

        return ResponseEntity.ok("Volunteer Enrollment deleted successfully");
    }

}