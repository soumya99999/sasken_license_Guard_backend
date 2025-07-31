package com.sasken.LicenseGuard.services.impl;

import com.sasken.LicenseGuard.dto.AdminLicenseAssignmentDTO;
import com.sasken.LicenseGuard.dto.LicenseAssignmentDTO;
import com.sasken.LicenseGuard.models.*;
import com.sasken.LicenseGuard.repository.*;
import com.sasken.LicenseGuard.services.LicenseAssignmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LicenseAssignmentServiceImpl implements LicenseAssignmentService {

    private final LicenseAssignmentRepository assignmentRepo;
    private final LicenseInventoryRepository licenseRepo;
    private final DepartmentRepository deptRepo;
    private final DeptLicenseRequestRepository requestRepo;

    public LicenseAssignmentServiceImpl(
            LicenseAssignmentRepository assignmentRepo,
            LicenseInventoryRepository licenseRepo,
            DepartmentRepository deptRepo,
            DeptLicenseRequestRepository requestRepo) {
        this.assignmentRepo = assignmentRepo;
        this.licenseRepo = licenseRepo;
        this.deptRepo = deptRepo;
        this.requestRepo = requestRepo;
    }

    @Override
    public void assignLicense(AdminLicenseAssignmentDTO dto) {
        LicenseInventory inventory = licenseRepo.findById(dto.getLicenseInventoryId())
                .orElseThrow(() -> new IllegalArgumentException("LicenseInventory not found"));

        Department department = deptRepo.findById(dto.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        if (inventory.getAvailableQuantity() < dto.getAssignedQuantity()) {
            throw new IllegalArgumentException("Insufficient license quantity available");
        }

        // Subtract from inventory
        inventory.setAvailableQuantity(inventory.getAvailableQuantity() - dto.getAssignedQuantity());
        licenseRepo.save(inventory);

        // Save assignment
        LicenseAssignment assignment = new LicenseAssignment();
        assignment.setAssignedQuantity(dto.getAssignedQuantity());
        assignment.setExpiresAt(dto.getExpiresAt());
        assignment.setLicenseInventory(inventory);
        assignment.setDepartment(department);

        assignmentRepo.save(assignment);

        // Update dept license request if present
        if (dto.getDeptLicenseRequestId() != null) {
            DeptLicenseRequest request = requestRepo.findById(dto.getDeptLicenseRequestId())
                    .orElseThrow(() -> new IllegalArgumentException("DeptLicenseRequest not found"));
            request.setStatus("APPROVED");
            requestRepo.save(request);
        }
    }

    @Override
    public List<LicenseAssignmentDTO> getAllAssignments() {
        return assignmentRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LicenseAssignmentDTO> getAssignmentsByDepartment(Long deptId) {
        return assignmentRepo.findByDepartmentId(deptId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private LicenseAssignmentDTO mapToDTO(LicenseAssignment entity) {
        LicenseAssignmentDTO dto = new LicenseAssignmentDTO();
        dto.setId(entity.getId());
        dto.setAssignedQuantity(entity.getAssignedQuantity());
        dto.setExpiresAt(entity.getExpiresAt());
        dto.setLicenseInventoryId(entity.getLicenseInventory().getId());
        dto.setDepartmentId(entity.getDepartment().getId());
        dto.setSoftwareName(entity.getLicenseInventory().getSoftwareName());
        return dto;
    }
}
