package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.OrganizationRequestDTO;
import com.example.demo.dto.OrganizationResponseDTO;
import com.example.demo.entity.Organization;
import com.example.demo.entity.SystemUser;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.repository.OrganizationRepository;
import com.example.demo.repository.SystemUserRepo;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final SystemUserRepo systemUserRepo;

    public OrganizationService(OrganizationRepository organizationRepository,
                               SystemUserRepo systemUserRepo) {

        this.organizationRepository = organizationRepository;
        this.systemUserRepo = systemUserRepo;
    }

    public OrganizationResponseDTO createOrganization(OrganizationRequestDTO dto){

        if(organizationRepository.existsByContactEmail(dto.getContactEmail())){
            throw new RuntimeException("Organization email already exists");
        }

        if(organizationRepository.existsByName(dto.getName())){
            throw new RuntimeException("Organization already exists");
        }

        SystemUser coordinator = systemUserRepo.findById(dto.getCoordinatorId())
                .orElseThrow(() -> new ResourceNotFound("Coordinator not found"));

        Organization organization = new Organization();

        organization.setName(dto.getName());
        organization.setMission(dto.getMission());
        organization.setAddress(dto.getAddress());
        organization.setContactEmail(dto.getContactEmail());
        organization.setContactPhone(dto.getContactPhone());
        organization.setFoundedYear(dto.getFoundedYear());
        organization.setCoordinator(coordinator);

        Organization saved = organizationRepository.save(organization);

        return mapToResponse(saved);
    }

    public List<OrganizationResponseDTO> getAllOrganizations(){

        return organizationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }

    public OrganizationResponseDTO getOrganization(Long id){

        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Organization not found"));

        return mapToResponse(organization);

    }

    public OrganizationResponseDTO updateOrganization(Long id,
                                                      OrganizationRequestDTO dto){

        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Organization not found"));

        SystemUser coordinator = systemUserRepo.findById(dto.getCoordinatorId())
                .orElseThrow(() -> new ResourceNotFound("Coordinator not found"));

        organization.setName(dto.getName());
        organization.setMission(dto.getMission());
        organization.setAddress(dto.getAddress());
        organization.setContactEmail(dto.getContactEmail());
        organization.setContactPhone(dto.getContactPhone());
        organization.setFoundedYear(dto.getFoundedYear());
        organization.setCoordinator(coordinator);

        Organization updated = organizationRepository.save(organization);

        return mapToResponse(updated);

    }

    public void deleteOrganization(Long id){

        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Organization not found"));

        organizationRepository.delete(organization);

    }

    private OrganizationResponseDTO mapToResponse(Organization organization){

        OrganizationResponseDTO dto = new OrganizationResponseDTO();

        dto.setId(organization.getId());
        dto.setName(organization.getName());
        dto.setMission(organization.getMission());
        dto.setAddress(organization.getAddress());
        dto.setContactEmail(organization.getContactEmail());
        dto.setContactPhone(organization.getContactPhone());
        dto.setFoundedYear(organization.getFoundedYear());
        dto.setStatus(organization.getStatus());

        if(organization.getCoordinator()!=null){

             dto.setCoordinatorId(
            organization.getCoordinator().getId());

            dto.setCoordinatorName(
                    organization.getCoordinator().getName());
        }

        dto.setCreatedAt(organization.getCreatedAt());
        dto.setUpdatedAt(organization.getUpdatedAt());

        return dto;

    }

}