package com.sasken.LicenseGuard.dto;

import java.time.LocalDate;

public class LicenseAssignmentDTO {
    private Long id;
    private Long licenseInventoryId;
    private Long departmentId;
    private int assignedQuantity;
    private LocalDate expiresAt;
    private String softwareName;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getLicenseInventoryId() { return licenseInventoryId; }
    public void setLicenseInventoryId(Long licenseInventoryId) { this.licenseInventoryId = licenseInventoryId; }

    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }

    public int getAssignedQuantity() { return assignedQuantity; }
    public void setAssignedQuantity(int assignedQuantity) { this.assignedQuantity = assignedQuantity; }

    public LocalDate getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDate expiresAt) { this.expiresAt = expiresAt; }

    public String getSoftwareName() { return softwareName; }
    public void setSoftwareName(String softwareName) { this.softwareName = softwareName; }
}
