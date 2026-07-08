package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.entity.Opportunity.OpportunityStatus;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OpportunityRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Start date is required")
    private LocalDateTime startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDateTime endDate;

    @Positive(message = "Maximum volunteers must be greater than 0")
    private Integer maxVolunteers;

    private OpportunityStatus status;

    @NotNull(message = "Organization ID is required")
    private Long organizationId;

    @NotBlank(message = "Required Skills is required")
private String requiredSkills;

    public OpportunityRequestDTO() {
    }

    

    public OpportunityRequestDTO(@NotBlank(message = "Title is required") String title,
            @NotBlank(message = "Description is required") String description,
            @NotBlank(message = "Location is required") String location,
            @NotNull(message = "Start date is required") LocalDateTime startDate,
            @NotNull(message = "End date is required") @Future(message = "End date must be in the future") LocalDateTime endDate,
            @Positive(message = "Maximum volunteers must be greater than 0") Integer maxVolunteers,
            OpportunityStatus status, @NotNull(message = "Organization ID is required") Long organizationId,
            @NotBlank(message = "Required Skills is required") String requiredSkills) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxVolunteers = maxVolunteers;
        this.status = status;
        this.organizationId = organizationId;
        this.requiredSkills = requiredSkills;
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

    public String getRequiredSkills() {
    return requiredSkills;
}

public void setRequiredSkills(String requiredSkills) {
    this.requiredSkills = requiredSkills;
}
}