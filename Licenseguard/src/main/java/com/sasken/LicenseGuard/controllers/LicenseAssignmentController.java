package com.sasken.LicenseGuard.controllers;

import com.sasken.LicenseGuard.dto.LicenseAssignmentDTO;
import com.sasken.LicenseGuard.services.LicenseAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/license-assignments")
public class LicenseAssignmentController {

    private final LicenseAssignmentService service;

    public LicenseAssignmentController(LicenseAssignmentService service) {
        this.service = service;
    }

    @PostMapping("/admin-issue")
    public ResponseEntity<LicenseAssignmentDTO> adminAssignToDept(@RequestBody LicenseAssignmentDTO dto) {
        return ResponseEntity.ok(service.issueLicenseToDeptHead(dto));
    }

    @PostMapping("/head-assign")
    public ResponseEntity<LicenseAssignmentDTO> deptHeadAssignToUser(@RequestBody LicenseAssignmentDTO dto) {
        return ResponseEntity.ok(service.assignLicenseToUser(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LicenseAssignmentDTO>> getAssignmentsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getAssignmentsByUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<LicenseAssignmentDTO>> getAllAssignments() {
        return ResponseEntity.ok(service.getAllAssignments());
    }
}
