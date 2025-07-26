package com.sasken.LicenseGuard.controllers;

import com.sasken.LicenseGuard.dto.DeptLicenseRequestDTO;
import com.sasken.LicenseGuard.services.DeptLicenseRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dept-license-requests")
public class DeptLicenseRequestController {

    private final DeptLicenseRequestService service;

    public DeptLicenseRequestController(DeptLicenseRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DeptLicenseRequestDTO> create(@RequestBody DeptLicenseRequestDTO dto) {
        return ResponseEntity.ok(service.createRequest(dto));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<DeptLicenseRequestDTO> approve(@PathVariable Long id) {
        return ResponseEntity.ok(service.approveRequest(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<DeptLicenseRequestDTO> reject(@PathVariable Long id, @RequestBody String reason) {
        return ResponseEntity.ok(service.rejectRequest(id, reason));
    }

    @GetMapping
    public ResponseEntity<List<DeptLicenseRequestDTO>> getAll() {
        return ResponseEntity.ok(service.getAllRequests());
    }

    @GetMapping("/department/{deptId}")
    public ResponseEntity<List<DeptLicenseRequestDTO>> getByDept(@PathVariable Long deptId) {
        return ResponseEntity.ok(service.getRequestsByDepartment(deptId));
    }
}
