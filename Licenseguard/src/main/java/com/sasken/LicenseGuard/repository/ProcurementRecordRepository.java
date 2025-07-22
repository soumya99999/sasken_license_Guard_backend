package com.sasken.LicenseGuard.repository;

import com.sasken.LicenseGuard.models.ProcurementRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcurementRecordRepository extends JpaRepository<ProcurementRecord, Long> {
    boolean existsByOrderNumber(String orderNumber);
}
