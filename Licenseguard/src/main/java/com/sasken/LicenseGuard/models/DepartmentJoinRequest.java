package com.sasken.LicenseGuard.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "department_join_request")
public class DepartmentJoinRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(nullable = false)
    private String status; // PENDING, APPROVED, REJECTED

    @Column(name = "requested_at", nullable = false)
    private LocalDate requestedAt;

    // === Custom Getters & Setters ===

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
	public LocalDate getRequestedAt() {
		return requestedAt;
	}
	public void setRequestedAt(LocalDate requestedAt) {
		this.requestedAt = requestedAt;
	}
    
    
}
