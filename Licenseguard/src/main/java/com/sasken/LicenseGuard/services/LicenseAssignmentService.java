package com.sasken.LicenseGuard.services;

import com.sasken.LicenseGuard.dto.LicenseAssignmentDTO;
import java.util.List;

public interface LicenseAssignmentService {
    LicenseAssignmentDTO assignLicenseToUser(LicenseAssignmentDTO dto); // Dept Head to User
    LicenseAssignmentDTO issueLicenseToDeptHead(LicenseAssignmentDTO dto); // Admin to Dept Head
    List<LicenseAssignmentDTO> getAssignmentsByUser(Long userId);
    List<LicenseAssignmentDTO> getAllAssignments();
}
