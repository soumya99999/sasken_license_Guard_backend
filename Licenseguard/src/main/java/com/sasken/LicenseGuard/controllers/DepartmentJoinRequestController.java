package com.sasken.LicenseGuard.controllers;

import com.sasken.LicenseGuard.dto.DepartmentJoinRequestDTO;
import com.sasken.LicenseGuard.services.DepartmentJoinRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/join-requests")
public class DepartmentJoinRequestController {

    private final DepartmentJoinRequestService service;

    public DepartmentJoinRequestController(DepartmentJoinRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DepartmentJoinRequestDTO> create(@RequestBody DepartmentJoinRequestDTO dto) {
        return ResponseEntity.ok(service.createRequest(dto));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<DepartmentJoinRequestDTO> approve(@PathVariable Long id) {
        return ResponseEntity.ok(service.approveRequest(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<DepartmentJoinRequestDTO> reject(@PathVariable Long id) {
        return ResponseEntity.ok(service.rejectRequest(id));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentJoinRequestDTO>> getAll() {
        return ResponseEntity.ok(service.getAllRequests());
    }

    @GetMapping("/department/{deptId}")
    public ResponseEntity<List<DepartmentJoinRequestDTO>> getByDepartment(@PathVariable Long deptId) {
        return ResponseEntity.ok(service.getByDepartmentId(deptId));
    }
}
