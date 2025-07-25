package com.sasken.LicenseGuard.controllers;

import com.sasken.LicenseGuard.dto.ProcurementRecordDTO;
import com.sasken.LicenseGuard.services.ProcurementRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procurements")
public class ProcurementRecordController {

    private final ProcurementRecordService procurementService;

    public ProcurementRecordController(ProcurementRecordService procurementService) {
        this.procurementService = procurementService;
    }

    @PostMapping
    public ResponseEntity<ProcurementRecordDTO> create(@RequestBody ProcurementRecordDTO dto) {
        return ResponseEntity.ok(procurementService.createProcurementRecord(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProcurementRecordDTO>> getAll() {
        return ResponseEntity.ok(procurementService.getAllProcurementRecords());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcurementRecordDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(procurementService.getProcurementById(id));
    }
}
