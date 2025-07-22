package com.sasken.LicenseGuard.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "procurement_record")
public class ProcurementRecord {

    @Id
    private Long poHeaderId;

    @Column(nullable = false, unique = true)
    private String orderNumber;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private String statusCode;

    @Column(nullable = false)
    private String supplier;

    @Column(nullable = false)
    private String softwareName;

    @Column(nullable = false)
    private Integer quantity;

    @Column
    private String currencyCode;

    @Column
    private Double total;

    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id")
    private Department department;

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

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}
