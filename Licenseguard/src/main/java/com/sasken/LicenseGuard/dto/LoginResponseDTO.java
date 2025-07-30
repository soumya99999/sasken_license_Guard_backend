package com.sasken.LicenseGuard.dto;

public class LoginResponseDTO {
    private Long userId;
    private String username;
    private String role;
    private Boolean isApproved;
    private String email;
    private Long departmentId;
    private String departmentName;

    // Custom Getters & Setters
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }
    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Long getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
