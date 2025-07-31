package com.sasken.LicenseGuard.services.impl;

import com.sasken.LicenseGuard.dto.DeptLicenseRequestDTO;
import com.sasken.LicenseGuard.models.Department;
import com.sasken.LicenseGuard.models.DeptLicenseRequest;
import com.sasken.LicenseGuard.models.User;
import com.sasken.LicenseGuard.repository.DepartmentRepository;
import com.sasken.LicenseGuard.repository.DeptLicenseRequestRepository;
import com.sasken.LicenseGuard.repository.UserRepository;
import com.sasken.LicenseGuard.services.DeptLicenseRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeptLicenseRequestServiceImpl implements DeptLicenseRequestService {

    private final DeptLicenseRequestRepository repo;
    private final DepartmentRepository deptRepo;
    private final UserRepository userRepo;

    public DeptLicenseRequestServiceImpl(
        DeptLicenseRequestRepository repo,
        DepartmentRepository deptRepo,
        UserRepository userRepo
    ) {
        this.repo = repo;
        this.deptRepo = deptRepo;
        this.userRepo = userRepo;
    }

    @Override
    public DeptLicenseRequestDTO createRequest(DeptLicenseRequestDTO dto) {
        Department dept = deptRepo.findById(dto.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        User user = userRepo.findById(dto.getRequestedByUserId())
                .orElseThrow(() -> new IllegalArgumentException("RequestedBy user not found"));

        DeptLicenseRequest request = new DeptLicenseRequest();
        request.setDepartment(dept);
        request.setSoftwareName(dto.getSoftwareName());
        request.setRequestedQuantity(dto.getRequestedQuantity());
        request.setStatus("PENDING");
        request.setRequestedAt(LocalDate.now());
        request.setRequestedBy(user);

        return mapToDTO(repo.save(request));
    }


    @Override
    public List<DeptLicenseRequestDTO> getAllRequests() {
        return repo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DeptLicenseRequestDTO> getRequestsByDepartment(Long departmentId) {
        return repo.findByDepartmentId(departmentId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private DeptLicenseRequestDTO mapToDTO(DeptLicenseRequest req) {
        DeptLicenseRequestDTO dto = new DeptLicenseRequestDTO();
        dto.setId(req.getId());
        dto.setDepartmentId(req.getDepartment().getId());
        dto.setSoftwareName(req.getSoftwareName());
        dto.setRequestedQuantity(req.getRequestedQuantity());
        dto.setRequestedAt(req.getRequestedAt());
        dto.setStatus(req.getStatus());
        dto.setRequestedByUserId(req.getRequestedBy().getId());
        dto.setRequestedByEmail(req.getRequestedBy().getEmail());
        return dto;
    }
}
