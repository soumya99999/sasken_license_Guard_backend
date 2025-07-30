package com.sasken.LicenseGuard.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasken.LicenseGuard.repository.LicenseInventoryRepository;
import com.sasken.LicenseGuard.models.LicenseInventory;
import com.sasken.LicenseGuard.models.ProcurementRecord;
import com.sasken.LicenseGuard.dto.LicenseReportDTO;

@Service
public class ReportService {

    @Autowired
    private LicenseInventoryRepository licenseRepo;

    public List<LicenseReportDTO> generateLicenseReport() {
        List<LicenseInventory> licenses = licenseRepo.findAll();

        return licenses.stream().map(license -> {
            String deptName = license.getDepartment().getName();
            ProcurementRecord record = license.getProcurementRecord();

            String procuredBy = record.getSupplier(); // Or change to `getUser().getName()` if assignee is a User
            LocalDate assignDate = record.getCreationDate(); // if exists

            return new LicenseReportDTO(
                    license.getSoftwareName(),
                    license.getLicenseKey(),
                    license.getPurchaseDate(),
                    license.getExpiryDate(),
                    license.getTotalQuantity(),
                    deptName,
                    procuredBy,
                    assignDate
            );
        }).collect(Collectors.toList());
    }
    public List<LicenseReportDTO> generateLicenseReportByDepartment(Long deptId) {
        List<LicenseInventory> licenses = licenseRepo.findByDepartmentId(deptId);

        return licenses.stream().map(license -> {
            String deptName = license.getDepartment().getName();
            ProcurementRecord record = license.getProcurementRecord();

            String procuredBy = record.getSupplier(); // or getUser().getName()
            LocalDate assignDate = record.getCreationDate(); // or assignment date

            return new LicenseReportDTO(
                    license.getSoftwareName(),
                    license.getLicenseKey(),
                    license.getPurchaseDate(),
                    license.getExpiryDate(),
                    license.getTotalQuantity(),
                    deptName,
                    procuredBy,
                    assignDate
            );
        }).collect(Collectors.toList());
    }

}
