package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.entity.Opportunity.OpportunityStatus;

public class OpportunityResponseDTO {

    private Long id;

    private String title;

    private String description;

    private String location;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer maxVolunteers;

    private OpportunityStatus status;

    private Long organizationId;

    private String organizationName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String requiredSkills;

    public OpportunityResponseDTO() {
    }

    
    public OpportunityResponseDTO(Long id, String title, String description, String location, LocalDateTime startDate,
            LocalDateTime endDate, Integer maxVolunteers, OpportunityStatus status, Long organizationId,
            String organizationName, LocalDateTime createdAt, LocalDateTime updatedAt, String requiredSkills) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxVolunteers = maxVolunteers;
        this.status = status;
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.requiredSkills = requiredSkills;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getMaxVolunteers() {
        return maxVolunteers;
    }

    public void setMaxVolunteers(Integer maxVolunteers) {
        this.maxVolunteers = maxVolunteers;
    }

    public OpportunityStatus getStatus() {
        return status;
    }

    public void setStatus(OpportunityStatus status) {
        this.status = status;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    public String getRequiredSkills() {
        return requiredSkills;
    }


    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
}