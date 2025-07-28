package com.sasken.LicenseGuard.services.impl;

import com.sasken.LicenseGuard.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendLicenseExpiryNotification(String deptHeadName, String deptHeadEmail, String departmentName, List<String[]> expiredLicenses) {
        String subject = "Expired Licenses Notification for " + departmentName + " department";
        if (expiredLicenses == null || expiredLicenses.isEmpty()) {
            System.out.println("No expired licenses to notify.");
            return;
        }
        StringBuilder html = new StringBuilder();
        html.append("<p>Hi ").append(deptHeadName).append(",</p>");
        html.append("<p>The following licenses are expiring in 7 days:</p>");
        html.append("<table border='1' cellpadding='5' cellspacing='0'>");
        html.append("<tr><th>Software Name</th><th>License Key</th><th>Expiry Date</th></tr>");

        for (String[] license : expiredLicenses) {
            html.append("<tr>")
                    .append("<td>").append(license[0]).append("</td>")
                    .append("<td>").append(license[1]).append("</td>")
                    .append("<td>").append(license[2]).append("</td>")
                    .append("</tr>");
        }

        html.append("</table>");
        html.append("<p>Thanks,<br>admin (Tracking Service)</p>");

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(deptHeadEmail);
            helper.setSubject(subject);
            helper.setText(html.toString(), true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
}

