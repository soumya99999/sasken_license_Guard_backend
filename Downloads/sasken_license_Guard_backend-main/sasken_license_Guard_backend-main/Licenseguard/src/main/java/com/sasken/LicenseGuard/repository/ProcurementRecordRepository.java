package com.sasken.LicenseGuard.repository;

import com.sasken.LicenseGuard.models.ProcurementRecord;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProcurementRecordRepository extends JpaRepository<ProcurementRecord, Long> {
    boolean existsByOrderNumber(String orderNumber);
    @Query
    ("SELECT p.department.name, SUM(p.total) FROM ProcurementRecord p GROUP BY p.department.name")
    List<Object[]> totalCostPerDepartment();
}
