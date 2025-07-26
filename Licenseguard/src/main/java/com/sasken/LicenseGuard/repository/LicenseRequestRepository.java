package com.sasken.LicenseGuard.repository;

import com.sasken.LicenseGuard.models.LicenseRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LicenseRequestRepository extends JpaRepository<LicenseRequest, Long> {
    List<LicenseRequest> findByRequestedById(Long userId);
}
