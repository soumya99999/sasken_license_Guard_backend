package com.sasken.LicenseGuard.controllers;

import com.sasken.LicenseGuard.dto.LicenseRequestDTO;
import com.sasken.LicenseGuard.services.LicenseRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/license-requests")
public class LicenseRequestController {

    private final LicenseRequestService requestService;

    public LicenseRequestController(LicenseRequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<LicenseRequestDTO> create(@RequestBody LicenseRequestDTO dto) {
        return ResponseEntity.ok(requestService.createRequest(dto));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<LicenseRequestDTO> approve(@PathVariable Long id, @RequestParam String reason) {
        return ResponseEntity.ok(requestService.approveRequest(id, reason));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<LicenseRequestDTO> reject(@PathVariable Long id, @RequestParam String reason) {
        return ResponseEntity.ok(requestService.rejectRequest(id, reason));
    }

    @GetMapping
    public ResponseEntity<List<LicenseRequestDTO>> getAll() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LicenseRequestDTO>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(requestService.getRequestsByUser(userId));
    }
}
