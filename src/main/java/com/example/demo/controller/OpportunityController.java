package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OpportunityRequestDTO;
import com.example.demo.dto.OpportunityResponseDTO;
import com.example.demo.service.OpportunityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/opportunities")
@Validated
public class OpportunityController {

    private final OpportunityService opportunityService;

    public OpportunityController(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    // Create Opportunity
    @PostMapping
    public ResponseEntity<OpportunityResponseDTO> createOpportunity(
            @Valid @RequestBody OpportunityRequestDTO dto) {

        OpportunityResponseDTO response = opportunityService.createOpportunity(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get All Opportunities
    @GetMapping
    public ResponseEntity<List<OpportunityResponseDTO>> getAllOpportunities() {

        return ResponseEntity.ok(opportunityService.getAllOpportunities());
    }

    // Get Opportunity By Id
    @GetMapping("/{id}")
    public ResponseEntity<OpportunityResponseDTO> getOpportunityById(
            @PathVariable Long id) {

        return ResponseEntity.ok(opportunityService.getOpportunityById(id));
    }

    // Update Opportunity
    @PutMapping("/{id}")
    public ResponseEntity<OpportunityResponseDTO> updateOpportunity(
            @PathVariable Long id,
            @Valid @RequestBody OpportunityRequestDTO dto) {

        return ResponseEntity.ok(
                opportunityService.updateOpportunity(id, dto));
    }

    // Delete Opportunity
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOpportunity(
            @PathVariable Long id) {

        opportunityService.deleteOpportunity(id);

        return ResponseEntity.ok("Opportunity deleted successfully");
    }

}