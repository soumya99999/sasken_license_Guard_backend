package com.sasken.LicenseGuard.services;

import com.sasken.LicenseGuard.dto.LicenseInventoryDTO;

import java.util.List;

public interface LicenseInventoryService {
    LicenseInventoryDTO createLicense(LicenseInventoryDTO dto);
    List<LicenseInventoryDTO> getAllLicenses();
    LicenseInventoryDTO getLicenseById(Long id);
    List<LicenseInventoryDTO> getByDepartment(Long departmentId);
}
