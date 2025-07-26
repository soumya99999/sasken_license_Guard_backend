package com.sasken.LicenseGuard.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sasken.LicenseGuard.dto.LicenseReportDTO;
import com.sasken.LicenseGuard.models.Department;


import com.sasken.LicenseGuard.repository.DepartmentRepository;

import com.sasken.LicenseGuard.services.ReportService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private DepartmentRepository depRepo;
    

    
    @GetMapping(value = "/licenses/downloadByDepartment/{deptId}", produces = "text/csv")
    public void downloadCSVByDepartment(@PathVariable Long deptId, HttpServletResponse response) throws IOException {
        List<LicenseReportDTO> report = reportService.generateLicenseReportByDepartment(deptId);
        Department department = depRepo.findByid(deptId);
        String deptName = department.getName().replaceAll("\\s+", "_");
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=license_report_department_" +deptName+ ".csv");

        PrintWriter writer = response.getWriter();

        // Write CSV header
        writer.println("Software Name,License Key,Purchase Date,Expiry Date,Total Quantity,Department,Procured By,Assignment Date");

        // Write CSV rows
        for (LicenseReportDTO dto : report) {
            writer.printf("\"%s\",\"%s\",\"%s\",\"%s\",%d,\"%s\",\"%s\",\"%s\"\n",
                    dto.getSoftwareName(),
                    dto.getLicenseKey(),
                    dto.getPurchaseDate(),
                    dto.getExpiryDate(),
                    dto.getTotalQuantity(),
                    dto.getDepartmentName(),
                    dto.getProcuredBy(),
                    dto.getAssignmentDate()
            );
        }

        writer.flush();
        writer.close();
    }
    
    @GetMapping(value = "/licenses/download", produces = "text/csv")
    public void downloadCSV(HttpServletResponse response) throws IOException {
        List<LicenseReportDTO> report = reportService.generateLicenseReport();
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=license_report.csv");

        PrintWriter writer = response.getWriter();
        
        // Write CSV header
        writer.println("Software Name,License Key,Purchase Date,Expiry Date,Total Quantity,Department,Procured By,Assignment Date");

        // Write data rows
        for (LicenseReportDTO dto : report) {
            writer.printf("\"%s\",\"%s\",\"%s\",\"%s\",%d,\"%s\",\"%s\",\"%s\"\n",
                    dto.getSoftwareName(),
                    dto.getLicenseKey(),
                    dto.getPurchaseDate(),
                    dto.getExpiryDate(),
                    dto.getTotalQuantity(),
                    dto.getDepartmentName(),
                    dto.getProcuredBy(),
                    dto.getAssignmentDate()
            );
        }

        writer.flush();
        writer.close();
    }
    
}

