package com.sasken.LicenseGuard.services.impl;

import com.sasken.LicenseGuard.dto.DepartmentDTO;
import com.sasken.LicenseGuard.dto.LicenseInventoryDTO;
import com.sasken.LicenseGuard.dto.UserDTO;
import com.sasken.LicenseGuard.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class TrackingServiceImpl implements TrackingService {

    @Autowired
    LicenseInventoryService licenseInventoryService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;

    // Run at 9:00 AM every day
    @Scheduled(cron = "0 0 9 * * ?")
    public void checkExpiringLicenses() {
        List<DepartmentDTO> allDepartments = departmentService.getAllDepartments();
        if (!allDepartments.isEmpty()){
            for (DepartmentDTO department : allDepartments) {
                sendExpirationEmailToDeptHead(department.getId());
            }
        }
    }

    private String formatDate(LocalDate date) {
        int day = date.getDayOfMonth();
        String dayStr = day + getDayOfMonthSuffix(day);
        String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int year = date.getYear();
        return String.format("%s %s %d", dayStr, month, year);
    }

    private String getDayOfMonthSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        switch (day % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }

    @Override
    public List<LicenseInventoryDTO> getExpiredLicensesByDepartment(Long departmentId) {

        //fetch all licenses(expiring and non expiring) from license inventory db by departmentId
        List<LicenseInventoryDTO> allLicensesByDepartment = licenseInventoryService.getByDepartment(departmentId);

        //filter out expiring licenses from the list above
        LocalDate sevenDaysFromNow = LocalDate.now().plusDays(7);

        List<LicenseInventoryDTO> expiringLicenses = allLicensesByDepartment.stream()
                .filter(license -> {
                    LocalDate expiry = license.getExpiryDate();
                    LocalDate today = LocalDate.now();
                    return expiry != null && expiry.isEqual(sevenDaysFromNow);
                })
                .collect(Collectors.toList());

        return expiringLicenses;
    }

    @Override
    public void sendExpirationEmailToDeptHead(Long departmentId) {
        DepartmentDTO department = departmentService.getDepartmentById(departmentId);

        Long headUserId = department.getHeadUserId();
        UserDTO departmentHead = userService.getUserById(headUserId);

        String deptHeadName = departmentHead.getUsername();
        String deptHeadEmail = departmentHead.getEmail();

        // Fetch soon-to-expire licenses
        List<LicenseInventoryDTO> expiringLicenses = getExpiredLicensesByDepartment(departmentId);


        List<String[]> expiredLicenses = expiringLicenses.stream()
                .map(license -> new String[]{
                        license.getSoftwareName(),
                        String.valueOf(license.getLicenseKey()),
                        formatDate(license.getExpiryDate())
                })
                .collect(Collectors.toList());
        if (!expiredLicenses.isEmpty()) {
            emailService.sendLicenseExpiryNotification(deptHeadName, deptHeadEmail, department.getName(), expiredLicenses);
        }


    }
}

