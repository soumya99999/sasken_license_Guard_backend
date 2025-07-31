package com.sasken.LicenseGuard.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "license_assignment")
public class LicenseAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int assignedQuantity;

    @Column(nullable = false)
    private LocalDate expiresAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "license_inventory_id")
    private LicenseInventory licenseInventory;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id")
    private Department department;


    // ===== Custom Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getAssignedQuantity() { return assignedQuantity; }
    public void setAssignedQuantity(int assignedQuantity) { this.assignedQuantity = assignedQuantity; }
    
	public LocalDate getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(LocalDate expiresAt) {
		this.expiresAt = expiresAt;
	}
	public LicenseInventory getLicenseInventory() { return licenseInventory; }
    public void setLicenseInventory(LicenseInventory licenseInventory) { this.licenseInventory = licenseInventory; }
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
    
    
}
