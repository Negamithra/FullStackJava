package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.OpportunityRequestDTO;
import com.example.demo.dto.OpportunityResponseDTO;
import com.example.demo.entity.Opportunity;
import com.example.demo.entity.Organization;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.repository.OpportunityRepository;
import com.example.demo.repository.OrganizationRepository;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private final OrganizationRepository organizationRepository;

    public OpportunityService(OpportunityRepository opportunityRepository,
                              OrganizationRepository organizationRepository) {
        this.opportunityRepository = opportunityRepository;
        this.organizationRepository = organizationRepository;
    }

    // CREATE
    public OpportunityResponseDTO createOpportunity(OpportunityRequestDTO dto) {

        if (opportunityRepository.existsByTitle(dto.getTitle())) {
            throw new RuntimeException("Opportunity already exists");
        }

        Organization organization = organizationRepository.findById(dto.getOrganizationId())
                .orElseThrow(() -> new ResourceNotFound("Organization not found"));

        Opportunity opportunity = new Opportunity();

        opportunity.setTitle(dto.getTitle());
        opportunity.setDescription(dto.getDescription());
        opportunity.setLocation(dto.getLocation());
        opportunity.setStartDate(dto.getStartDate());
        opportunity.setEndDate(dto.getEndDate());
        opportunity.setMaxVolunteers(dto.getMaxVolunteers());
        opportunity.setStatus(dto.getStatus());
        opportunity.setOrganization(organization);
        opportunity.setRequiredSkills(dto.getRequiredSkills());

        Opportunity saved = opportunityRepository.save(opportunity);

        return mapToResponse(saved);
    }

    // GET ALL
    public List<OpportunityResponseDTO> getAllOpportunities() {

        return opportunityRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public OpportunityResponseDTO getOpportunityById(Long id) {

        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Opportunity not found"));

        return mapToResponse(opportunity);
    }

    // UPDATE
    public OpportunityResponseDTO updateOpportunity(Long id,
                                                    OpportunityRequestDTO dto) {

        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Opportunity not found"));

        Organization organization = organizationRepository.findById(dto.getOrganizationId())
                .orElseThrow(() -> new ResourceNotFound("Organization not found"));

        opportunity.setTitle(dto.getTitle());
        opportunity.setDescription(dto.getDescription());
        opportunity.setLocation(dto.getLocation());
        opportunity.setStartDate(dto.getStartDate());
        opportunity.setEndDate(dto.getEndDate());
        opportunity.setMaxVolunteers(dto.getMaxVolunteers());
        opportunity.setStatus(dto.getStatus());
        opportunity.setOrganization(organization);
        opportunity.setRequiredSkills(dto.getRequiredSkills());

        Opportunity updated = opportunityRepository.save(opportunity);

        return mapToResponse(updated);
    }

    // DELETE
    public void deleteOpportunity(Long id) {

        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Opportunity not found"));

        opportunityRepository.delete(opportunity);
    }

    // ENTITY -> RESPONSE DTO
    private OpportunityResponseDTO mapToResponse(Opportunity opportunity) {

        OpportunityResponseDTO dto = new OpportunityResponseDTO();

        dto.setId(opportunity.getId());
        dto.setTitle(opportunity.getTitle());
        dto.setDescription(opportunity.getDescription());
        dto.setLocation(opportunity.getLocation());
        dto.setStartDate(opportunity.getStartDate());
        dto.setEndDate(opportunity.getEndDate());
        dto.setMaxVolunteers(opportunity.getMaxVolunteers());
        dto.setStatus(opportunity.getStatus());
        dto.setRequiredSkills(opportunity.getRequiredSkills());

        if (opportunity.getOrganization() != null) {
            dto.setOrganizationId(opportunity.getOrganization().getId());
            dto.setOrganizationName(opportunity.getOrganization().getName());
        }

        dto.setCreatedAt(opportunity.getCreatedAt());
        dto.setUpdatedAt(opportunity.getUpdatedAt());

        return dto;
    }
}