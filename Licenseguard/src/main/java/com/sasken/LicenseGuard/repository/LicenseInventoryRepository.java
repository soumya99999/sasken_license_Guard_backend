package com.sasken.LicenseGuard.repository;

import com.sasken.LicenseGuard.models.LicenseInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LicenseInventoryRepository extends JpaRepository<LicenseInventory, Long> {
    List<LicenseInventory> findByDepartmentId(Long departmentId);
}
