package com.sasken.LicenseGuard.models;

import jakarta.persistence.*;
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
    private LocalDateTime assignedAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "license_inventory_id")
    private LicenseInventory licenseInventory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "assigned_to_id")
    private User assignedTo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "assigned_by_id")
    private User assignedBy; // Admin or Dept Head

    // ===== Custom Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getAssignedQuantity() { return assignedQuantity; }
    public void setAssignedQuantity(int assignedQuantity) { this.assignedQuantity = assignedQuantity; }

    public LocalDateTime getAssignedAt() { return assignedAt; }
    public void setAssignedAt(LocalDateTime assignedAt) { this.assignedAt = assignedAt; }

    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }

    public LicenseInventory getLicenseInventory() { return licenseInventory; }
    public void setLicenseInventory(LicenseInventory licenseInventory) { this.licenseInventory = licenseInventory; }

    public User getAssignedTo() { return assignedTo; }
    public void setAssignedTo(User assignedTo) { this.assignedTo = assignedTo; }

    public User getAssignedBy() { return assignedBy; }
    public void setAssignedBy(User assignedBy) { this.assignedBy = assignedBy; }
}
