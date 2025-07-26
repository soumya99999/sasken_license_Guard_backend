package com.sasken.LicenseGuard.dto;

import java.time.LocalDateTime;

public class LicenseAssignmentDTO {
    private Long id;
    private int assignedQuantity;
    private LocalDateTime assignedAt;
    private LocalDateTime expiresAt;
    private Long licenseInventoryId;
    private Long assignedToUserId;
    private Long assignedByUserId;

    // ====== Custom Getters & Setters ======
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getAssignedQuantity() { return assignedQuantity; }
    public void setAssignedQuantity(int assignedQuantity) { this.assignedQuantity = assignedQuantity; }

    public LocalDateTime getAssignedAt() { return assignedAt; }
    public void setAssignedAt(LocalDateTime assignedAt) { this.assignedAt = assignedAt; }

    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }

    public Long getLicenseInventoryId() { return licenseInventoryId; }
    public void setLicenseInventoryId(Long licenseInventoryId) { this.licenseInventoryId = licenseInventoryId; }

    public Long getAssignedToUserId() { return assignedToUserId; }
    public void setAssignedToUserId(Long assignedToUserId) { this.assignedToUserId = assignedToUserId; }

    public Long getAssignedByUserId() { return assignedByUserId; }
    public void setAssignedByUserId(Long assignedByUserId) { this.assignedByUserId = assignedByUserId; }
}
