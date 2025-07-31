package com.sasken.LicenseGuard.services;

import com.sasken.LicenseGuard.dto.DeptLicenseRequestDTO;

import java.util.List;

public interface DeptLicenseRequestService {
    DeptLicenseRequestDTO createRequest(DeptLicenseRequestDTO dto);
    List<DeptLicenseRequestDTO> getAllRequests();
    List<DeptLicenseRequestDTO> getRequestsByDepartment(Long departmentId);
}
