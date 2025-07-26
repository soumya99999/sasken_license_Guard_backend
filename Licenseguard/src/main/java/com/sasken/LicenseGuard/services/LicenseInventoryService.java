package com.sasken.LicenseGuard.services;

import com.sasken.LicenseGuard.dto.LicenseInventoryDTO;

import java.util.List;

public interface LicenseInventoryService {
    LicenseInventoryDTO createLicense(LicenseInventoryDTO dto);
    LicenseInventoryDTO getLicenseById(Long id);
    List<LicenseInventoryDTO> getAllLicenses();
    List<LicenseInventoryDTO> getByDepartment(Long departmentId);
}
