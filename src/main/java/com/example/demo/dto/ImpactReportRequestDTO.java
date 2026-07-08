package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ImpactReportRequestDTO {

    @NotBlank(message = "Summary cannot be empty")
    @Size(min = 10, max = 500, message = "Summary must contain 10 to 500 characters")
    private String summary;

    @Positive(message = "Hours contributed must be greater than 0")
    private Double hoursContributed;

    @Min(value = 1, message = "Beneficiaries served must be at least 1")
    private Integer beneficiariesServed;

    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    private Integer rating;

    @NotNull(message = "Volunteer Enrollment ID is required")
    private Long volunteerEnrollmentId;

    public ImpactReportRequestDTO() {
    }

    public ImpactReportRequestDTO(String summary,
                                  Double hoursContributed,
                                  Integer beneficiariesServed,
                                  Integer rating,
                                  Long volunteerEnrollmentId) {
        this.summary = summary;
        this.hoursContributed = hoursContributed;
        this.beneficiariesServed = beneficiariesServed;
        this.rating = rating;
        this.volunteerEnrollmentId = volunteerEnrollmentId;
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

    public Long getVolunteerEnrollmentId() {
        return volunteerEnrollmentId;
    }

    public void setVolunteerEnrollmentId(Long volunteerEnrollmentId) {
        this.volunteerEnrollmentId = volunteerEnrollmentId;
    }
}