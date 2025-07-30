package com.sasken.LicenseGuard.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LicenseRequestDTO {
    private Long id;
    private String softwareName;
    private String systemId;
    private Long userId;
    private LocalDate requestedAt;
    
	private String status;
    private String reason;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSoftwareName() { return softwareName; }
    public void setSoftwareName(String softwareName) { this.softwareName = softwareName; }

    public String getSystemId() { return systemId; }
    public void setSystemId(String systemId) { this.systemId = systemId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    

    public LocalDate getRequestedAt() {
		return requestedAt;
	}
	public void setRequestedAt(LocalDate requestedAt) {
		this.requestedAt = requestedAt;
	}
	public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
