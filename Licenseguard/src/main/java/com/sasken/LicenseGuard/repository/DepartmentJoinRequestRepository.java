package com.sasken.LicenseGuard.repository;

import com.sasken.LicenseGuard.models.DepartmentJoinRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DepartmentJoinRequestRepository extends JpaRepository<DepartmentJoinRequest, Long> {
    List<DepartmentJoinRequest> findByDepartmentId(Long departmentId);
    List<DepartmentJoinRequest> findByUserId(Long userId);
}
