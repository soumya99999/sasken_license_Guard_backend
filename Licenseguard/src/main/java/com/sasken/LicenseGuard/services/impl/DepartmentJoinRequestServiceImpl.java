package com.sasken.LicenseGuard.services.impl;

import com.sasken.LicenseGuard.dto.DepartmentJoinRequestDTO;
import com.sasken.LicenseGuard.models.Department;
import com.sasken.LicenseGuard.models.DepartmentJoinRequest;
import com.sasken.LicenseGuard.models.User;
import com.sasken.LicenseGuard.repository.DepartmentJoinRequestRepository;
import com.sasken.LicenseGuard.repository.DepartmentRepository;
import com.sasken.LicenseGuard.repository.UserRepository;
import com.sasken.LicenseGuard.services.DepartmentJoinRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentJoinRequestServiceImpl implements DepartmentJoinRequestService {

    private final DepartmentJoinRequestRepository requestRepo;
    private final UserRepository userRepo;
    private final DepartmentRepository departmentRepo;

    public DepartmentJoinRequestServiceImpl(
            DepartmentJoinRequestRepository requestRepo,
            UserRepository userRepo,
            DepartmentRepository departmentRepo
    ) {
        this.requestRepo = requestRepo;
        this.userRepo = userRepo;
        this.departmentRepo = departmentRepo;
    }

    @Override
    public DepartmentJoinRequestDTO createRequest(DepartmentJoinRequestDTO dto) {
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Department department = departmentRepo.findById(dto.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        DepartmentJoinRequest request = new DepartmentJoinRequest();
        request.setUser(user);
        request.setDepartment(department);
        request.setStatus("PENDING");
        request.setRequestedAt(LocalDateTime.now());

        return mapToDTO(requestRepo.save(request));
    }

    @Override
    public DepartmentJoinRequestDTO approveRequest(Long requestId) {
        DepartmentJoinRequest request = requestRepo.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Join request not found"));

        // Update join request status
        request.setStatus("APPROVED");

        // Update user's department and approval status
        User user = request.getUser();
        Department department = request.getDepartment();

        if (user != null && department != null) {
            user.setIsApproved(true);
            user.setDepartment(department);
            userRepo.save(user);
        }

        return mapToDTO(requestRepo.save(request));
    }

    @Override
    public DepartmentJoinRequestDTO rejectRequest(Long requestId) {
        DepartmentJoinRequest request = requestRepo.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Join request not found"));

        request.setStatus("REJECTED");
        return mapToDTO(requestRepo.save(request));
    }

    @Override
    public List<DepartmentJoinRequestDTO> getAllRequests() {
        return requestRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DepartmentJoinRequestDTO> getByDepartmentId(Long departmentId) {
        return requestRepo.findByDepartmentId(departmentId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private DepartmentJoinRequestDTO mapToDTO(DepartmentJoinRequest request) {
        DepartmentJoinRequestDTO dto = new DepartmentJoinRequestDTO();
        dto.setId(request.getId());
        dto.setUserId(request.getUser() != null ? request.getUser().getId() : null);
        dto.setDepartmentId(request.getDepartment() != null ? request.getDepartment().getId() : null);
        dto.setStatus(request.getStatus());
        dto.setRequestedAt(request.getRequestedAt());
        return dto;
    }
}
