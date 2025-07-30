package com.sasken.LicenseGuard.controllers;

import com.sasken.LicenseGuard.dto.LoginResponseDTO;
import com.sasken.LicenseGuard.dto.UserLoginDTO;
import com.sasken.LicenseGuard.models.User;
import com.sasken.LicenseGuard.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepo;

    public AuthController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO loginDTO) {
        User user = userRepo.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username"));

        if (!user.getPassword().equals(loginDTO.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password");
        }

        // Map response
        LoginResponseDTO res = new LoginResponseDTO();
        res.setUserId(user.getId());
        res.setUsername(user.getUsername());
        res.setRole(user.getRole().name());
        res.setIsApproved(user.getIsApproved());
        res.setEmail(user.getEmail());
        res.setDepartmentId(user.getDepartment() != null ? user.getDepartment().getId() : null);

        // ✅ Set department name if user is DEPT_HEAD and department is assigned
        if ("DEPT_HEAD".equals(user.getRole().name()) && user.getDepartment() != null) {
            res.setDepartmentName(user.getDepartment().getName());
        }

        return ResponseEntity.ok(res);
    }

}
