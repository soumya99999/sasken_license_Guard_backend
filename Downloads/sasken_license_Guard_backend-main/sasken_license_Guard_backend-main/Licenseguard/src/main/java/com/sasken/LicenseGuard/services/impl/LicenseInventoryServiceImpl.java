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

    public LicenseInventoryServiceImpl(LicenseInventoryRepository licenseRepo,
                                       DepartmentRepository departmentRepo,
                                       ProcurementRecordRepository procurementRepo) {
        this.licenseRepo = licenseRepo;
        this.departmentRepo = departmentRepo;
        this.procurementRepo = procurementRepo;
    }

    @Override
    public LicenseInventoryDTO createLicense(LicenseInventoryDTO dto) {
        Department department = departmentRepo.findById(dto.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        ProcurementRecord procurement = procurementRepo.findById(dto.getProcurementId())
                .orElseThrow(() -> new IllegalArgumentException("Procurement record not found"));

        LicenseInventory license = new LicenseInventory();
        license.setSoftwareName(dto.getSoftwareName());
        license.setLicenseKey(dto.getLicenseKey());
        license.setPurchaseDate(dto.getPurchaseDate());
        license.setExpiryDate(dto.getExpiryDate());
        license.setTotalQuantity(dto.getTotalQuantity());
        license.setDepartment(department);
        license.setProcurementRecord(procurement);

        return mapToDTO(licenseRepo.save(license));
    }

    @Override
    public List<LicenseInventoryDTO> getAllLicenses() {
        return licenseRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LicenseInventoryDTO getLicenseById(Long id) {
        LicenseInventory license = licenseRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("License not found"));
        return mapToDTO(license);
    }

    @Override
    public List<LicenseInventoryDTO> getByDepartment(Long departmentId) {
        return licenseRepo.findByDepartmentId(departmentId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private LicenseInventoryDTO mapToDTO(LicenseInventory license) {
        LicenseInventoryDTO dto = new LicenseInventoryDTO();
        dto.setLicenseId(license.getLicenseId());
        dto.setSoftwareName(license.getSoftwareName());
        dto.setLicenseKey(license.getLicenseKey());
        dto.setPurchaseDate(license.getPurchaseDate());
        dto.setExpiryDate(license.getExpiryDate());
        dto.setTotalQuantity(license.getTotalQuantity());
        dto.setDepartmentId(license.getDepartment().getId());
        dto.setProcurementId(license.getProcurementRecord().getPoHeaderId());
        return dto;
    }
}
