package com.sasken.LicenseGuard.services.impl;

import com.sasken.LicenseGuard.dto.ProcurementRecordDTO;
import com.sasken.LicenseGuard.models.Department;
import com.sasken.LicenseGuard.models.ProcurementRecord;
import com.sasken.LicenseGuard.repository.DepartmentRepository;
import com.sasken.LicenseGuard.repository.ProcurementRecordRepository;
import com.sasken.LicenseGuard.services.ProcurementRecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcurementRecordServiceImpl implements ProcurementRecordService {

    private final ProcurementRecordRepository repository;
    private final DepartmentRepository departmentRepo;

    public ProcurementRecordServiceImpl(ProcurementRecordRepository repository,
                                        DepartmentRepository departmentRepo) {
        this.repository = repository;
        this.departmentRepo = departmentRepo;
    }

    @Override
    public ProcurementRecordDTO createProcurementRecord(ProcurementRecordDTO dto) {
        if (repository.existsByOrderNumber(dto.getOrderNumber())) {
            throw new IllegalArgumentException("Order number already exists.");
        }

        Department department = departmentRepo.findById(dto.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        ProcurementRecord record = new ProcurementRecord();
        record.setPoHeaderId(dto.getPoHeaderId());
        record.setOrderNumber(dto.getOrderNumber());
        record.setCreationDate(dto.getCreationDate());
        record.setStatusCode(dto.getStatusCode());
        record.setSupplier(dto.getSupplier());
        record.setSoftwareName(dto.getSoftwareName());
        record.setQuantity(dto.getQuantity());
        record.setCurrencyCode(dto.getCurrencyCode());
        record.setTotal(dto.getTotal());
        record.setDepartment(department); // ✅ important

        ProcurementRecord saved = repository.save(record);
        return mapToDTO(saved);
    }

    @Override
    public List<ProcurementRecordDTO> getAllProcurementRecords() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProcurementRecordDTO getProcurementById(Long poHeaderId) {
        ProcurementRecord record = repository.findById(poHeaderId)
                .orElseThrow(() -> new IllegalArgumentException("Record not found"));
        return mapToDTO(record);
    }

    private ProcurementRecordDTO mapToDTO(ProcurementRecord record) {
        ProcurementRecordDTO dto = new ProcurementRecordDTO();
        dto.setPoHeaderId(record.getPoHeaderId());
        dto.setOrderNumber(record.getOrderNumber());
        dto.setCreationDate(record.getCreationDate());
        dto.setStatusCode(record.getStatusCode());
        dto.setSupplier(record.getSupplier());
        dto.setSoftwareName(record.getSoftwareName());
        dto.setQuantity(record.getQuantity());
        dto.setCurrencyCode(record.getCurrencyCode());
        dto.setTotal(record.getTotal());
        dto.setDepartmentId(record.getDepartment() != null ? record.getDepartment().getId() : null);
        return dto;
    }
}
