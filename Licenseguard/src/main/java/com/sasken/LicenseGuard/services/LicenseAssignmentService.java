package com.sasken.LicenseGuard.services;

import com.sasken.LicenseGuard.dto.AdminLicenseAssignmentDTO;
import com.sasken.LicenseGuard.dto.LicenseAssignmentDTO;

import java.util.List;

public interface LicenseAssignmentService {
    void assignLicense(AdminLicenseAssignmentDTO dto);
    List<LicenseAssignmentDTO> getAllAssignments();
    List<LicenseAssignmentDTO> getAssignmentsByDepartment(Long deptId);
}
