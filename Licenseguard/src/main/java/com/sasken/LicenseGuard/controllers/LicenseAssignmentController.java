package com.sasken.LicenseGuard.controllers;

import com.sasken.LicenseGuard.dto.AdminLicenseAssignmentDTO;
import com.sasken.LicenseGuard.dto.LicenseAssignmentDTO;
import com.sasken.LicenseGuard.services.LicenseAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/license-assignments")
public class LicenseAssignmentController {

    private final LicenseAssignmentService assignmentService;

    public LicenseAssignmentController(LicenseAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    // ✅ Assign license to department (Admin)
    @PostMapping
    public ResponseEntity<String> assignLicense(@RequestBody AdminLicenseAssignmentDTO dto) {
        assignmentService.assignLicense(dto);
        return ResponseEntity.ok("License assigned successfully.");
    }

    // ✅ Get all assignments
    @GetMapping
    public ResponseEntity<List<LicenseAssignmentDTO>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }

    // ✅ Get assignments by department
    @GetMapping("/department/{deptId}")
    public ResponseEntity<List<LicenseAssignmentDTO>> getByDepartment(@PathVariable Long deptId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByDepartment(deptId));
    }
}
