package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "impact_reports")
public class ImpactReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    private LocalDateTime submittedAt;

    @ManyToOne
    @JoinColumn(name = "volunteer_enrollment_id", nullable = false)
    private VolunteerEnrollment volunteerEnrollment;

    public ImpactReport() {
    }

    @PrePersist
    public void prePersist() {
        this.submittedAt = LocalDateTime.now();
    }

    public ImpactReport(Long id,
                        String summary,
                        Double hoursContributed,
                        Integer beneficiariesServed,
                        Integer rating,
                        LocalDateTime submittedAt,
                        VolunteerEnrollment volunteerEnrollment) {
        this.id = id;
        this.summary = summary;
        this.hoursContributed = hoursContributed;
        this.beneficiariesServed = beneficiariesServed;
        this.rating = rating;
        this.submittedAt = submittedAt;
        this.volunteerEnrollment = volunteerEnrollment;
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

    public VolunteerEnrollment getVolunteerEnrollment() {
        return volunteerEnrollment;
    }

    public void setVolunteerEnrollment(VolunteerEnrollment volunteerEnrollment) {
        this.volunteerEnrollment = volunteerEnrollment;
    }
}