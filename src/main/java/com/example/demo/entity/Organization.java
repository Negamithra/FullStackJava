package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Organization name is required")
    @Size(min = 3, max = 100, message = "Organization name must be between 3 and 100 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Mission is required")
    @Size(min = 10, max = 500, message = "Mission must be between 10 and 500 characters")
    @Column(length = 500, nullable = false)
    private String mission;

    @NotBlank(message = "Address is required")
    @Column(nullable = false)
    private String address;

    @Email(message = "Invalid email")
    @Column(nullable = false, unique = true)
    private String contactEmail;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must contain exactly 10 digits")
    @Column(nullable = false)
    private String contactPhone;

    @Min(value = 1900, message = "Founded year is invalid")
    @Max(value = 2100, message = "Founded year is invalid")
    private Integer foundedYear;

    @Enumerated(EnumType.STRING)
    private OrganizationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "coordinator_id")
private SystemUser coordinator;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Opportunity> opportunities = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum OrganizationStatus {
        PENDING,
        APPROVED,
        SUSPENDED
    }

    public Organization() {
    }

    public Organization(Long id, String name, String mission, String address,
            String contactEmail, String contactPhone,
            Integer foundedYear,
            OrganizationStatus status,
            SystemUser coordinator,
            List<Opportunity> opportunities,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        this.id = id;
        this.name = name;
        this.mission = mission;
        this.address = address;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.foundedYear = foundedYear;
        this.status = status;
        this.coordinator = coordinator;
        this.opportunities = opportunities;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (status == null) {
            status = OrganizationStatus.PENDING;
        }
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(Integer foundedYear) {
        this.foundedYear = foundedYear;
    }

    public OrganizationStatus getStatus() {
        return status;
    }

    public void setStatus(OrganizationStatus status) {
        this.status = status;
    }

    public SystemUser getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(SystemUser coordinator) {
        this.coordinator = coordinator;
    }

    public List<Opportunity> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<Opportunity> opportunities) {
        this.opportunities = opportunities;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}