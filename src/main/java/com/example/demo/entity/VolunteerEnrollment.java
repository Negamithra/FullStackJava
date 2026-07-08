package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Size;

@Entity
public class VolunteerEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "volunteer_id", nullable = false)
    private SystemUser volunteer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opportunity_id", nullable = false)
    private Opportunity opportunity;

    private LocalDateTime enrolledAt;

    private LocalDateTime approvedAt;

    private Double hoursLogged = 0.0;

    @Size(max = 500, message = "Notes cannot exceed 500 characters")
    private String notes;

    @OneToMany(mappedBy = "volunteerEnrollment",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ImpactReport> impactReports = new ArrayList<>();

    public VolunteerEnrollment() {
    }

    public VolunteerEnrollment(Long id, SystemUser volunteer,
            Opportunity opportunity,
            LocalDateTime enrolledAt,
            LocalDateTime approvedAt,
            Double hoursLogged,
            String notes,
            List<ImpactReport> impactReports) {

        this.id = id;
        this.volunteer = volunteer;
        this.opportunity = opportunity;
        this.enrolledAt = enrolledAt;
        this.approvedAt = approvedAt;
        this.hoursLogged = hoursLogged;
        this.notes = notes;
        this.impactReports = impactReports;
    }

    @PrePersist
    public void onCreate() {
        enrolledAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SystemUser getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(SystemUser volunteer) {
        this.volunteer = volunteer;
    }

    public Opportunity getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
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

    public List<ImpactReport> getImpactReports() {
        return impactReports;
    }

    public void setImpactReports(List<ImpactReport> impactReports) {
        this.impactReports = impactReports;
    }
}