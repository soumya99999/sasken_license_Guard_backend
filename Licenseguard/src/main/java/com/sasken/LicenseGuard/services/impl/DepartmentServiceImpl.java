package com.sasken.LicenseGuard.services.impl;

import com.sasken.LicenseGuard.dto.DepartmentDTO;
import com.sasken.LicenseGuard.models.Department;
import com.sasken.LicenseGuard.models.User;
import com.sasken.LicenseGuard.repository.DepartmentRepository;
import com.sasken.LicenseGuard.repository.UserRepository;
import com.sasken.LicenseGuard.services.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository deptRepo;
    private final UserRepository userRepo;

    public DepartmentServiceImpl(DepartmentRepository deptRepo, UserRepository userRepo) {
        this.deptRepo = deptRepo;
        this.userRepo = userRepo;
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO dto) {
        User head = null;
        if (dto.getHeadUserId() != null) {
            head = userRepo.findById(dto.getHeadUserId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Head User ID"));
        }

        Department department = new Department();
        department.setName(dto.getName());
        department.setHeadUser(head);

        Department saved = deptRepo.save(department);
        return mapToDTO(saved);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        return deptRepo.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return deptRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDepartment(Long id) {
        deptRepo.deleteById(id);
    }

    private DepartmentDTO mapToDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setHeadUserId(department.getHeadUser().getId());
        return dto;
    }
}
