package com.sasken.LicenseGuard.controllers;

import com.sasken.LicenseGuard.dto.LicenseInventoryDTO;
import com.sasken.LicenseGuard.services.LicenseInventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/licenses")
public class LicenseInventoryController {

    private final LicenseInventoryService licenseService;

    public LicenseInventoryController(LicenseInventoryService licenseService) {
        this.licenseService = licenseService;
    }

    @PostMapping
    public ResponseEntity<LicenseInventoryDTO> create(@RequestBody LicenseInventoryDTO dto) {
        return ResponseEntity.ok(licenseService.createLicense(dto));
    }

    @GetMapping
    public ResponseEntity<List<LicenseInventoryDTO>> getAll() {
        return ResponseEntity.ok(licenseService.getAllLicenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LicenseInventoryDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(licenseService.getLicenseById(id));
    }

    @GetMapping("/department/{deptId}")
    public ResponseEntity<List<LicenseInventoryDTO>> getByDept(@PathVariable Long deptId) {
        return ResponseEntity.ok(licenseService.getByDepartment(deptId));
    }
}
