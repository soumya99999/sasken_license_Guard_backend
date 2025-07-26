package com.sasken.LicenseGuard.services;

import com.sasken.LicenseGuard.dto.LicenseRequestDTO;

import java.util.List;

public interface LicenseRequestService {
    LicenseRequestDTO createRequest(LicenseRequestDTO dto);
    LicenseRequestDTO approveRequest(Long id, String reason);
    LicenseRequestDTO rejectRequest(Long id, String reason);
    List<LicenseRequestDTO> getAllRequests();
    List<LicenseRequestDTO> getRequestsByUser(Long userId);
}
