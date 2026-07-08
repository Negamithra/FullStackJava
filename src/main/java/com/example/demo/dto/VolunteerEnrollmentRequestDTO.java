package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VolunteerEnrollmentRequestDTO {

    @NotNull(message = "Volunteer ID is required")
    private Long volunteerId;

    @NotNull(message = "Opportunity ID is required")
    private Long opportunityId;

    private Double hoursLogged;

    @Size(max = 500, message = "Notes cannot exceed 500 characters")
    private String notes;

    public VolunteerEnrollmentRequestDTO() {
    }

    public VolunteerEnrollmentRequestDTO(Long volunteerId,
                                         Long opportunityId,
                                         Double hoursLogged,
                                         String notes) {
        this.volunteerId = volunteerId;
        this.opportunityId = opportunityId;
        this.hoursLogged = hoursLogged;
        this.notes = notes;
    }

    public Long getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Long volunteerId) {
        this.volunteerId = volunteerId;
    }

    public Long getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Long opportunityId) {
        this.opportunityId = opportunityId;
    }

    public Double getHoursLogged() {
        return hoursLogged;
    }

    public void setHoursLogged(Double hoursLogged) {
        this.hoursLogged = hoursLogged;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}