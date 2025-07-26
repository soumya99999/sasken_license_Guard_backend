package com.sasken.LicenseGuard.repository;

import com.sasken.LicenseGuard.models.DeptLicenseRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeptLicenseRequestRepository extends JpaRepository<DeptLicenseRequest, Long> {
    List<DeptLicenseRequest> findByDepartmentId(Long departmentId);
}
