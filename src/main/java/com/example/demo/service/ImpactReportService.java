package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ImpactReportRequestDTO;
import com.example.demo.dto.ImpactReportResponseDTO;
import com.example.demo.entity.ImpactReport;
import com.example.demo.entity.VolunteerEnrollment;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.repository.ImpactReportRepo;
import com.example.demo.repository.VolunteerEnrollmentRepo;

@Service
public class ImpactReportService {

    private final ImpactReportRepo impactReportRepo;
    private final VolunteerEnrollmentRepo enrollmentRepo;

    public ImpactReportService(ImpactReportRepo impactReportRepo,
                               VolunteerEnrollmentRepo enrollmentRepo) {
        this.impactReportRepo = impactReportRepo;
        this.enrollmentRepo = enrollmentRepo;
    }

    // CREATE
    public ImpactReportResponseDTO createReport(ImpactReportRequestDTO dto) {

        VolunteerEnrollment enrollment = enrollmentRepo
                .findById(dto.getVolunteerEnrollmentId())
                .orElseThrow(() ->
                        new ResourceNotFound("Volunteer Enrollment not found"));

        ImpactReport report = new ImpactReport();

        report.setSummary(dto.getSummary());
        report.setHoursContributed(dto.getHoursContributed());
        report.setBeneficiariesServed(dto.getBeneficiariesServed());
        report.setRating(dto.getRating());
        report.setVolunteerEnrollment(enrollment);

        ImpactReport saved = impactReportRepo.save(report);

        return mapToResponse(saved);
    }

    // GET ALL
    public List<ImpactReportResponseDTO> getAllReports() {

        return impactReportRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public ImpactReportResponseDTO getReportById(Long id) {

        ImpactReport report = impactReportRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFound("Impact Report not found"));

        return mapToResponse(report);
    }

    // UPDATE
    public ImpactReportResponseDTO updateReport(Long id,
                                                ImpactReportRequestDTO dto) {

        ImpactReport report = impactReportRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFound("Impact Report not found"));

        VolunteerEnrollment enrollment = enrollmentRepo
                .findById(dto.getVolunteerEnrollmentId())
                .orElseThrow(() ->
                        new ResourceNotFound("Volunteer Enrollment not found"));

        report.setSummary(dto.getSummary());
        report.setHoursContributed(dto.getHoursContributed());
        report.setBeneficiariesServed(dto.getBeneficiariesServed());
        report.setRating(dto.getRating());
        report.setVolunteerEnrollment(enrollment);

        ImpactReport updated = impactReportRepo.save(report);

        return mapToResponse(updated);
    }

    // DELETE
    public void deleteReport(Long id) {

        ImpactReport report = impactReportRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFound("Impact Report not found"));

        impactReportRepo.delete(report);
    }

    // ENTITY -> DTO
    private ImpactReportResponseDTO mapToResponse(ImpactReport report) {

        ImpactReportResponseDTO dto = new ImpactReportResponseDTO();

        dto.setId(report.getId());
        dto.setSummary(report.getSummary());
        dto.setHoursContributed(report.getHoursContributed());
        dto.setBeneficiariesServed(report.getBeneficiariesServed());
        dto.setRating(report.getRating());
        dto.setSubmittedAt(report.getSubmittedAt());

        if (report.getVolunteerEnrollment() != null) {

            VolunteerEnrollment enrollment = report.getVolunteerEnrollment();

            dto.setVolunteerEnrollmentId(enrollment.getId());

            if (enrollment.getVolunteer() != null) {
                dto.setVolunteerId(enrollment.getVolunteer().getId());
                dto.setVolunteerName(enrollment.getVolunteer().getName());
            }

            if (enrollment.getOpportunity() != null) {
                dto.setOpportunityId(enrollment.getOpportunity().getId());
                dto.setOpportunityTitle(enrollment.getOpportunity().getTitle());
            }
        }

        return dto;
    }
}