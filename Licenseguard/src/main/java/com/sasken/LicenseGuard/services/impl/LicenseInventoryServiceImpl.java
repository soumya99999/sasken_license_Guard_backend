package com.sasken.LicenseGuard.services.impl;

import com.sasken.LicenseGuard.dto.LicenseInventoryDTO;
import com.sasken.LicenseGuard.models.Department;
import com.sasken.LicenseGuard.models.LicenseInventory;
import com.sasken.LicenseGuard.models.ProcurementRecord;
import com.sasken.LicenseGuard.repository.DepartmentRepository;
import com.sasken.LicenseGuard.repository.LicenseInventoryRepository;
import com.sasken.LicenseGuard.repository.ProcurementRecordRepository;
import com.sasken.LicenseGuard.services.LicenseInventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LicenseInventoryServiceImpl implements LicenseInventoryService {

    private final LicenseInventoryRepository licenseRepo;
    private final DepartmentRepository departmentRepo;
    private final ProcurementRecordRepository procurementRepo;

    public LicenseInventoryServiceImpl(
            LicenseInventoryRepository licenseRepo,
            DepartmentRepository departmentRepo,
            ProcurementRecordRepository procurementRepo) {
        this.licenseRepo = licenseRepo;
        this.departmentRepo = departmentRepo;
        this.procurementRepo = procurementRepo;
    }

    @Override
    public LicenseInventoryDTO createLicense(LicenseInventoryDTO dto) {
        Department department = departmentRepo.findById(dto.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid department ID: " + dto.getDepartmentId()));

        ProcurementRecord procurement = procurementRepo.findById(dto.getProcurementRecordId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid procurement record ID: " + dto.getProcurementRecordId()));

        LicenseInventory license = new LicenseInventory();
        license.setSoftwareName(dto.getSoftwareName());
        license.setLicenseKey(dto.getLicenseKey());
        license.setPurchaseDate(dto.getPurchaseDate());
        license.setExpiryDate(dto.getExpiryDate());
        license.setTotalQuantity(dto.getTotalQuantity());
        license.setAvailableQuantity(dto.getTotalQuantity()); // Initially all are available

        license.setDepartment(department);
        license.setProcurementRecord(procurement);

        LicenseInventory saved = licenseRepo.save(license);
        return mapToDTO(saved);
    }

    @Override
    public LicenseInventoryDTO getLicenseById(Long id) {
        LicenseInventory license = licenseRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("License not found with id: " + id));
        return mapToDTO(license);
    }

    @Override
    public List<LicenseInventoryDTO> getAllLicenses() {
        return licenseRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LicenseInventoryDTO> getByDepartment(Long departmentId) {
        return licenseRepo.findByDepartmentId(departmentId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private LicenseInventoryDTO mapToDTO(LicenseInventory entity) {
        LicenseInventoryDTO dto = new LicenseInventoryDTO();
        dto.setId(entity.getId());
        dto.setSoftwareName(entity.getSoftwareName());
        dto.setLicenseKey(entity.getLicenseKey());
        dto.setTotalQuantity(entity.getTotalQuantity());
        dto.setAvailableQuantity(entity.getAvailableQuantity());
        dto.setPurchaseDate(entity.getPurchaseDate());
        dto.setExpiryDate(entity.getExpiryDate());
        dto.setDepartmentId(entity.getDepartment().getId());
        dto.setProcurementRecordId(entity.getProcurementRecord().getPoHeaderId());
        return dto;
    }
}
