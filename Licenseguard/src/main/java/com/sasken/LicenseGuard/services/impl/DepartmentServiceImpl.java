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

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, UserRepository userRepository) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO dto) {
        User headUser = userRepository.findById(dto.getHeadUserId())
                .orElseThrow(() -> new IllegalArgumentException("Head user not found"));

        Department dept = new Department();
        dept.setName(dto.getName());
        dept.setHeadUser(headUser);

        return mapToDTO(departmentRepository.save(dept));
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));
        return mapToDTO(dept);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private DepartmentDTO mapToDTO(Department dept) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(dept.getId());
        dto.setName(dept.getName());
        dto.setHeadUserId(dept.getHeadUser() != null ? dept.getHeadUser().getId() : null);
        return dto;
    }
}
