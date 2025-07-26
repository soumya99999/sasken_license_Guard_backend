package com.sasken.LicenseGuard.repository;

import com.sasken.LicenseGuard.models.Department;


import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Department findByid(Long deptId);
}
