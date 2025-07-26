package com.sasken.LicenseGuard.services;

import com.sasken.LicenseGuard.dto.DepartmentJoinRequestDTO;
import java.util.List;

public interface DepartmentJoinRequestService {

    DepartmentJoinRequestDTO createJoinRequest(DepartmentJoinRequestDTO dto);

    DepartmentJoinRequestDTO approveRequest(Long requestId);

    DepartmentJoinRequestDTO rejectRequest(Long requestId);

    List<DepartmentJoinRequestDTO> getAll();

    List<DepartmentJoinRequestDTO> getRequestsByDepartment(Long deptId);
}
