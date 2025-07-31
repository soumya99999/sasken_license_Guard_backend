package com.sasken.LicenseGuard.dto;

import java.time.LocalDate;

public class AdminLicenseAssignmentDTO {
    private Long departmentId;
    private Long licenseInventoryId;
    private int assignedQuantity;
    private LocalDate expiresAt;
    private Long deptLicenseRequestId;
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getLicenseInventoryId() {
		return licenseInventoryId;
	}
	public void setLicenseInventoryId(Long licenseInventoryId) {
		this.licenseInventoryId = licenseInventoryId;
	}
	public int getAssignedQuantity() {
		return assignedQuantity;
	}
	public void setAssignedQuantity(int assignedQuantity) {
		this.assignedQuantity = assignedQuantity;
	}
	public LocalDate getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(LocalDate expiresAt) {
		this.expiresAt = expiresAt;
	}
	public Long getDeptLicenseRequestId() {
		return deptLicenseRequestId;
	}
	public void setDeptLicenseRequestId(Long deptLicenseRequestId) {
		this.deptLicenseRequestId = deptLicenseRequestId;
	}

    // Getters & Setters
    
}
