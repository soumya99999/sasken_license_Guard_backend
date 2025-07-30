package com.sasken.LicenseGuard.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "dept_license_request")
public class DeptLicenseRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(nullable = false)
    private String softwareName;

    @Column(nullable = false)
    private int requestedQuantity;

    @Column(nullable = false)
    private String status; // PENDING, APPROVED, REJECTED

    @Column
    private String reason;

    @Column(name = "requested_at", nullable = false)
    private LocalDate requestedAt;
    
    @ManyToOne
    @JoinColumn(name = "requested_by_user_id", nullable = false)
    private User requestedBy;


    // ===== Getters & Setters =====

    public User getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(User requestedBy) {
		this.requestedBy = requestedBy;
	}
	public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public String getSoftwareName() { return softwareName; }
    public void setSoftwareName(String softwareName) { this.softwareName = softwareName; }

    public int getRequestedQuantity() { return requestedQuantity; }
    public void setRequestedQuantity(int requestedQuantity) { this.requestedQuantity = requestedQuantity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
	public LocalDate getRequestedAt() {
		return requestedAt;
	}
	public void setRequestedAt(LocalDate requestedAt) {
		this.requestedAt = requestedAt;
	}
    
    
}
