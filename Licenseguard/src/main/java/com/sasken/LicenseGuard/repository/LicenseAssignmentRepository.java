package com.sasken.LicenseGuard.repository;

import com.sasken.LicenseGuard.models.LicenseAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LicenseAssignmentRepository extends JpaRepository<LicenseAssignment, Long> {
    List<LicenseAssignment> findByDepartmentId(Long departmentId);
}
