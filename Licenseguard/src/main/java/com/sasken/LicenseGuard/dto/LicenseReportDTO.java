package com.sasken.LicenseGuard.dto;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class LicenseReportDTO {
    private String softwareName;
    private String licenseKey;
    private LocalDate purchaseDate;
    private LocalDate expiryDate;
    private Integer totalQuantity;
    private String departmentName;
    private String procuredBy;
    private LocalDate assignmentDate;

    public LicenseReportDTO() {}

    public LicenseReportDTO(String softwareName, String licenseKey, LocalDate purchaseDate,
                            LocalDate expiryDate, Integer totalQuantity, String departmentName,
                            String procuredBy, LocalDate assignmentDate) {
        this.softwareName = softwareName;
        this.licenseKey = licenseKey;
        this.purchaseDate = purchaseDate;
        this.expiryDate = expiryDate;
        this.totalQuantity = totalQuantity;
        this.departmentName = departmentName;
        this.procuredBy = procuredBy;
        this.assignmentDate = assignmentDate;
    }

    // Getters and Setters
    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getProcuredBy() {
        return procuredBy;
    }

    public void setProcuredBy(String procuredBy) {
        this.procuredBy = procuredBy;
    }

	public LocalDate getAssignmentDate() {
		return assignmentDate;
	}

	public void setAssignmentDate(LocalDate assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

    
}
