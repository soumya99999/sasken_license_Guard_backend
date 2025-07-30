package com.sasken.LicenseGuard.services;

import com.sasken.LicenseGuard.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO createDepartment(DepartmentDTO dto);
    DepartmentDTO updateDepartment(Long id, DepartmentDTO dto);
    DepartmentDTO getDepartmentById(Long id);
    List<DepartmentDTO> getAllDepartments();
    void deleteDepartment(Long id);
}
