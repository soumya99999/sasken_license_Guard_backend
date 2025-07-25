package com.sasken.LicenseGuard.repository;

import com.sasken.LicenseGuard.models.LicenseInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LicenseInventoryRepository extends JpaRepository<LicenseInventory, Long> {
    List<LicenseInventory> findByDepartmentId(Long departmentId);
    List<LicenseInventory> findByExpiryDateBefore(LocalDate date);
    @Query
    ("SELECT l.department.name, COUNT(l) FROM LicenseInventory l GROUP BY l.department.name")
    List<Object[]> countByDepartment();
}
