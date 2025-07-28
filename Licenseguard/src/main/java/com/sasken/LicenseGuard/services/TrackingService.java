package com.sasken.LicenseGuard.services;

import com.sasken.LicenseGuard.dto.LicenseInventoryDTO;

import java.util.List;

public interface TrackingService {

    List<LicenseInventoryDTO> getExpiredLicensesByDepartment(Long departmentId);

    void sendExpirationEmailToDeptHead(Long departmentId);
}
