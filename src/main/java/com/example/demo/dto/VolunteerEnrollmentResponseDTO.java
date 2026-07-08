package com.example.demo.dto;

import java.time.LocalDateTime;

public class VolunteerEnrollmentResponseDTO {

    private Long id;

    private Long volunteerId;

    private String volunteerName;

    private Long opportunityId;

    private String opportunityTitle;

    private LocalDateTime enrolledAt;

    private LocalDateTime approvedAt;

    private Double hoursLogged;

    private String notes;

    public VolunteerEnrollmentResponseDTO() {
    }

    public VolunteerEnrollmentResponseDTO(Long id,  Long volunteerId,  String volunteerName,  Long opportunityId,  String opportunityTitle,  LocalDateTime enrolledAt,  LocalDateTime approvedAt,  Double hoursLogged,  String notes) {
        this.id = id;
        this.volunteerId = volunteerId;
        this.volunteerName = volunteerName;
        this.opportunityId = opportunityId;
        this.opportunityTitle = opportunityTitle;
        this.enrolledAt = enrolledAt;
        this.approvedAt = approvedAt;
        this.hoursLogged = hoursLogged;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Long volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public Long getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Long opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getOpportunityTitle() {
        return opportunityTitle;
    }

    public void setOpportunityTitle(String opportunityTitle) {
        this.opportunityTitle = opportunityTitle;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public LocalDateTime getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(LocalDateTime approvedAt) {
        this.approvedAt = approvedAt;
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