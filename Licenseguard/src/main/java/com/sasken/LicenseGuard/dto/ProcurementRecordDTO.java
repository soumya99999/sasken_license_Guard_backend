package com.sasken.LicenseGuard.dto;

import java.time.LocalDateTime;

public class ProcurementRecordDTO {
    private Long poHeaderId;
    private String orderNumber;
    private LocalDateTime creationDate;
    private String statusCode;
    private String supplier;
    private String softwareName;
    private Integer quantity;
    private String currencyCode;
    private Double total;
    private Long departmentId;

    // Getters and Setters
    public Long getPoHeaderId() { return poHeaderId; }
    public void setPoHeaderId(Long poHeaderId) { this.poHeaderId = poHeaderId; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }

    public String getStatusCode() { return statusCode; }
    public void setStatusCode(String statusCode) { this.statusCode = statusCode; }

    public String getSupplier() { return supplier; }
    public void setSupplier(String supplier) { this.supplier = supplier; }

    public String getSoftwareName() { return softwareName; }
    public void setSoftwareName(String softwareName) { this.softwareName = softwareName; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getCurrencyCode() { return currencyCode; }
    public void setCurrencyCode(String currencyCode) { this.currencyCode = currencyCode; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }
}
