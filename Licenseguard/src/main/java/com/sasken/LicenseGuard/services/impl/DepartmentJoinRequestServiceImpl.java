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

    private final DepartmentJoinRequestRepository joinRepo;
    private final UserRepository userRepo;
    private final DepartmentRepository deptRepo;

    public DepartmentJoinRequestServiceImpl(DepartmentJoinRequestRepository joinRepo,
                                            UserRepository userRepo,
                                            DepartmentRepository deptRepo) {
        this.joinRepo = joinRepo;
        this.userRepo = userRepo;
        this.deptRepo = deptRepo;
    }

    @Override
    public DepartmentJoinRequestDTO createJoinRequest(DepartmentJoinRequestDTO dto) {
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Department department = deptRepo.findById(dto.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        DepartmentJoinRequest req = new DepartmentJoinRequest();
        req.setUser(user);
        req.setDepartment(department);
        req.setRequestedAt(LocalDateTime.now());
        req.setStatus("PENDING");

        return mapToDTO(joinRepo.save(req));
    }

    @Override
    public DepartmentJoinRequestDTO approveRequest(Long requestId) {
        DepartmentJoinRequest req = joinRepo.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        req.setStatus("APPROVED");

        // set department to user
        User user = req.getUser();
        user.setDepartment(req.getDepartment());
        userRepo.save(user);

        return mapToDTO(joinRepo.save(req));
    }

    @Override
    public DepartmentJoinRequestDTO rejectRequest(Long requestId) {
        DepartmentJoinRequest req = joinRepo.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
        req.setStatus("REJECTED");
        return mapToDTO(joinRepo.save(req));
    }

    @Override
    public List<DepartmentJoinRequestDTO> getAll() {
        return joinRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<DepartmentJoinRequestDTO> getRequestsByDepartment(Long deptId) {
        return joinRepo.findByDepartmentId(deptId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private DepartmentJoinRequestDTO mapToDTO(DepartmentJoinRequest entity) {
        DepartmentJoinRequestDTO dto = new DepartmentJoinRequestDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setDepartmentId(entity.getDepartment().getId());
        dto.setRequestedAt(entity.getRequestedAt());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
