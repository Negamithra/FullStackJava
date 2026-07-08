package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.VolunteerEnrollmentRequestDTO;
import com.example.demo.dto.VolunteerEnrollmentResponseDTO;
import com.example.demo.entity.Opportunity;
import com.example.demo.entity.SystemUser;
import com.example.demo.entity.VolunteerEnrollment;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.repository.OpportunityRepository;
import com.example.demo.repository.SystemUserRepo;
import com.example.demo.repository.VolunteerEnrollmentRepo;

@Service
public class VolunteerEnrollmentService {

    private final VolunteerEnrollmentRepo enrollmentRepo;
    private final SystemUserRepo userRepo;
    private final OpportunityRepository opportunityRepo;

    public VolunteerEnrollmentService(
            VolunteerEnrollmentRepo enrollmentRepo,
            SystemUserRepo userRepo,
            OpportunityRepository opportunityRepo) {

        this.enrollmentRepo = enrollmentRepo;
        this.userRepo = userRepo;
        this.opportunityRepo = opportunityRepo;
    }

    // CREATE
    public VolunteerEnrollmentResponseDTO createEnrollment(
            VolunteerEnrollmentRequestDTO dto) {

        SystemUser volunteer = userRepo.findById(dto.getVolunteerId())
                .orElseThrow(() ->
                        new ResourceNotFound("Volunteer not found"));

        Opportunity opportunity = opportunityRepo.findById(dto.getOpportunityId())
                .orElseThrow(() ->
                        new ResourceNotFound("Opportunity not found"));

        VolunteerEnrollment enrollment = new VolunteerEnrollment();

        enrollment.setVolunteer(volunteer);
        enrollment.setOpportunity(opportunity);
        enrollment.setHoursLogged(dto.getHoursLogged());
        enrollment.setNotes(dto.getNotes());

        VolunteerEnrollment saved = enrollmentRepo.save(enrollment);

        return mapToResponse(saved);
    }

    // GET ALL
    public List<VolunteerEnrollmentResponseDTO> getAllEnrollments() {

        return enrollmentRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }

    // GET BY ID
    public VolunteerEnrollmentResponseDTO getEnrollmentById(Long id) {

        VolunteerEnrollment enrollment = enrollmentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFound("Enrollment not found"));

        return mapToResponse(enrollment);

    }

    // UPDATE
    public VolunteerEnrollmentResponseDTO updateEnrollment(
            Long id,
            VolunteerEnrollmentRequestDTO dto) {

        VolunteerEnrollment enrollment = enrollmentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFound("Enrollment not found"));

        SystemUser volunteer = userRepo.findById(dto.getVolunteerId())
                .orElseThrow(() ->
                        new ResourceNotFound("Volunteer not found"));

        Opportunity opportunity = opportunityRepo.findById(dto.getOpportunityId())
                .orElseThrow(() ->
                        new ResourceNotFound("Opportunity not found"));

        enrollment.setVolunteer(volunteer);
        enrollment.setOpportunity(opportunity);
        enrollment.setHoursLogged(dto.getHoursLogged());
        enrollment.setNotes(dto.getNotes());

        VolunteerEnrollment updated = enrollmentRepo.save(enrollment);

        return mapToResponse(updated);
    }

    // DELETE
    public void deleteEnrollment(Long id) {

        VolunteerEnrollment enrollment = enrollmentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFound("Enrollment not found"));

        enrollmentRepo.delete(enrollment);

    }

    // ENTITY -> DTO
    private VolunteerEnrollmentResponseDTO mapToResponse(
            VolunteerEnrollment enrollment) {

        VolunteerEnrollmentResponseDTO dto =
                new VolunteerEnrollmentResponseDTO();

        dto.setId(enrollment.getId());

        if (enrollment.getVolunteer() != null) {
            dto.setVolunteerId(enrollment.getVolunteer().getId());
            dto.setVolunteerName(enrollment.getVolunteer().getName());
        }

        if (enrollment.getOpportunity() != null) {
            dto.setOpportunityId(enrollment.getOpportunity().getId());
            dto.setOpportunityTitle(enrollment.getOpportunity().getTitle());
        }

        dto.setEnrolledAt(enrollment.getEnrolledAt());
        dto.setApprovedAt(enrollment.getApprovedAt());
        dto.setHoursLogged(enrollment.getHoursLogged());
        dto.setNotes(enrollment.getNotes());

        return dto;
    }
}