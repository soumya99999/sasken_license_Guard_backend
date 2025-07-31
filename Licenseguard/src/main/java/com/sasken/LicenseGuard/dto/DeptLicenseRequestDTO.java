package com.sasken.LicenseGuard.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DeptLicenseRequestDTO {
    private Long id;
    private Long departmentId;
    private String softwareName;
    private int requestedQuantity;
    private String status;
    private LocalDate requestedAt;
    private Long requestedByUserId;
    private String requestedByEmail;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getSoftwareName() {
		return softwareName;
	}
	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}
	public int getRequestedQuantity() {
		return requestedQuantity;
	}
	public void setRequestedQuantity(int requestedQuantity) {
		this.requestedQuantity = requestedQuantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getRequestedAt() {
		return requestedAt;
	}
	public void setRequestedAt(LocalDate requestedAt) {
		this.requestedAt = requestedAt;
	}
	public Long getRequestedByUserId() {
		return requestedByUserId;
	}
	public void setRequestedByUserId(Long requestedByUserId) {
		this.requestedByUserId = requestedByUserId;
	}
	public String getRequestedByEmail() {
		return requestedByEmail;
	}
	public void setRequestedByEmail(String requestedByEmail) {
		this.requestedByEmail = requestedByEmail;
	}

    // Getters & Setters
    
    
}

    