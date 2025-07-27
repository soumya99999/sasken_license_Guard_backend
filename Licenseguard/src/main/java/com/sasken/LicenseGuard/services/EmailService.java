package com.sasken.LicenseGuard.services;

import java.util.List;

public interface EmailService {

    void sendLicenseExpiryNotification(String deptHeadName, String deptHeadEmail, String departmentName, List<String[]> expiredLicenses);
}