package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ImpactReportRequestDTO;
import com.example.demo.dto.ImpactReportResponseDTO;
import com.example.demo.service.ImpactReportService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/impact-reports")
public class ImpactReportController {

    private final ImpactReportService impactReportService;

    public ImpactReportController(ImpactReportService impactReportService) {
        this.impactReportService = impactReportService;
    }

    // Create Impact Report
    @PostMapping
    public ResponseEntity<ImpactReportResponseDTO> createReport(
            @Valid @RequestBody ImpactReportRequestDTO dto) {

        ImpactReportResponseDTO response =
                impactReportService.createReport(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get All Impact Reports
    @GetMapping
    public ResponseEntity<List<ImpactReportResponseDTO>> getAllReports() {

        return ResponseEntity.ok(
                impactReportService.getAllReports());
    }

    // Get Impact Report By ID
    @GetMapping("/{id}")
    public ResponseEntity<ImpactReportResponseDTO> getReportById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                impactReportService.getReportById(id));
    }

    // Update Impact Report
    @PutMapping("/{id}")
    public ResponseEntity<ImpactReportResponseDTO> updateReport(
            @PathVariable Long id,
            @Valid @RequestBody ImpactReportRequestDTO dto) {

        return ResponseEntity.ok(
                impactReportService.updateReport(id, dto));
    }

    // Delete Impact Report
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReport(
            @PathVariable Long id) {

        impactReportService.deleteReport(id);

        return ResponseEntity.ok("Impact Report deleted successfully");
    }

}