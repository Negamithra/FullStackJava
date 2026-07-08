package com.example.demo.dto;

import java.time.LocalDateTime;

public class ImpactReportResponseDTO {

    private Long id;

    private String summary;

    private Double hoursContributed;

    private Integer beneficiariesServed;

    private Integer rating;

    private LocalDateTime submittedAt;

    private Long volunteerEnrollmentId;

    private Long volunteerId;

    private String volunteerName;

    private Long opportunityId;

    private String opportunityTitle;

    public ImpactReportResponseDTO() {
    }

    public ImpactReportResponseDTO(Long id,
                                   String summary,
                                   Double hoursContributed,
                                   Integer beneficiariesServed,
                                   Integer rating,
                                   LocalDateTime submittedAt,
                                   Long volunteerEnrollmentId,
                                   Long volunteerId,
                                   String volunteerName,
                                   Long opportunityId,
                                   String opportunityTitle) {
        this.id = id;
        this.summary = summary;
        this.hoursContributed = hoursContributed;
        this.beneficiariesServed = beneficiariesServed;
        this.rating = rating;
        this.submittedAt = submittedAt;
        this.volunteerEnrollmentId = volunteerEnrollmentId;
        this.volunteerId = volunteerId;
        this.volunteerName = volunteerName;
        this.opportunityId = opportunityId;
        this.opportunityTitle = opportunityTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Double getHoursContributed() {
        return hoursContributed;
    }

    public void setHoursContributed(Double hoursContributed) {
        this.hoursContributed = hoursContributed;
    }

    public Integer getBeneficiariesServed() {
        return beneficiariesServed;
    }

    public void setBeneficiariesServed(Integer beneficiariesServed) {
        this.beneficiariesServed = beneficiariesServed;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Long getVolunteerEnrollmentId() {
        return volunteerEnrollmentId;
    }

    public void setVolunteerEnrollmentId(Long volunteerEnrollmentId) {
        this.volunteerEnrollmentId = volunteerEnrollmentId;
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
}