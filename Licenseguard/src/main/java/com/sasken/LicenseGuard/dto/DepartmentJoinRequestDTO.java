package com.sasken.LicenseGuard.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DepartmentJoinRequestDTO {

    private Long id;
    private Long userId;
    private Long departmentId;
    private String status;
    private String email;
    private LocalDate requestedAt;

    // === Getters & Setters ===

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    
	public LocalDate getRequestedAt() {
		return requestedAt;
	}
	public void setRequestedAt(LocalDate requestedAt) {
		this.requestedAt = requestedAt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
