# 📦 License Inventory Management Module - LicenseGuard

## 📘 Overview

The **License Inventory Management** module is a core component of the LicenseGuard system, designed to **store, manage, and track software licenses** purchased by various departments in an organization. It ensures integration with procurement records, supports license availability monitoring, and enables downstream modules such as renewal alerts, license assignment, and compliance tracking.

---

## 🎯 Module Objective

- Track and store department-wise software license details.
- Ensure traceability of license origin through procurement record mapping.
- Maintain quantity metrics for usage and assignment.
- Provide APIs to retrieve license data by ID, department, or procurement record.

---

## 🧱 Entity: `LicenseInventory`

**Mapped Table:** `license_inventory`

### Main Fields

| Field Name         | Data Type     | Description                                 |
|--------------------|---------------|---------------------------------------------|
| `softwareName`     | String        | Name of the software                        |
| `licenseKey`       | String (Unique) | Unique license key assigned to the software |
| `totalQuantity`    | int           | Total number of licenses purchased          |
| `availableQuantity`| int           | Number of licenses currently available      |
| `purchaseDate`     | LocalDate     | Date when the license was purchased         |
| `expiryDate`       | LocalDate     | Date when the license will expire           |

### Relationships

| Relationship Type | Related Entity       | Join Column         | Description                                              |
|-------------------|----------------------|----------------------|----------------------------------------------------------|
| `@ManyToOne`      | Department           | `department_id`      | Associates license with a specific department            |
| `@ManyToOne`      | ProcurementRecord    | `procurement_id`     | Links license to its source procurement record           |

---

## 🌐 REST API Endpoints

### Base URL: `/api/licenses`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/` | Create a new license record |
| `GET`  | `/` | Retrieve all licenses |
| `GET`  | `/{id}` | Get license by ID |
| `GET`  | `/department/{deptId}` | Get licenses by department |
| `GET`  | `/procurement/{procId}` | Get licenses by procurement record |

---

## 🔄 Dependencies

- **Inputs Required:**
  - Valid Department ID
  - Valid Procurement Record ID

- **Used By:**
  - **Expiry & Renewal Module**: uses `expiryDate` to schedule alerts.
  - **Assignment Module**: uses `availableQuantity` for license allocation.
  - **Compliance Monitoring**: checks usage limits.
  - **Reporting Module**: generates audit and usage reports.

---

## 🛠️ Technologies Used

- **Backend Framework**: Spring Boot (Java)
- **ORM**: Hibernate / JPA
- **Database**: MySQL (or compatible relational DB)
- **DTO Layer**: For mapping data between Entity and Controller

---

## 📌 Notes

- All license keys are expected to be unique.
- Department and procurement records must exist before creating a license.
- Ensure correct date formatting (`yyyy-MM-dd`) in all POST requests.

---

## 👨‍💻 Developer

**Soumya Ranjan Barik & Nihal Kumar**  
Module Owner – License Inventory Management  
LicenseGuard System  


---

