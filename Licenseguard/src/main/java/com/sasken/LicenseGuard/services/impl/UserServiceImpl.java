package com.sasken.LicenseGuard.services.impl;

import com.sasken.LicenseGuard.dto.UserDTO;
import com.sasken.LicenseGuard.enums.Role;
import com.sasken.LicenseGuard.models.Department;
import com.sasken.LicenseGuard.models.User;
import com.sasken.LicenseGuard.repository.DepartmentRepository;
import com.sasken.LicenseGuard.repository.UserRepository;
import com.sasken.LicenseGuard.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final DepartmentRepository deptRepo;

    public UserServiceImpl(UserRepository userRepo, DepartmentRepository deptRepo) {
        this.userRepo = userRepo;
        this.deptRepo = deptRepo;
    }

    @Override
    public UserDTO createUser(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        Role role = Role.valueOf(dto.getRole());
        user.setRole(role);

        if (role == Role.DEPT_HEAD || role == Role.ADMIN) {
            user.setIsApproved(true);
        } else {
            user.setIsApproved(dto.getIsApproved() != null ? dto.getIsApproved() : false);
        }

        // ✅ Assign department if provided
        Department dept = null;
        if (dto.getDepartmentId() != null) {
            dept = deptRepo.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid department ID"));
            user.setDepartment(dept);
        }

        User saved = userRepo.save(user);

        // ✅ Set as headUser in Department only if DEPT_HEAD and department is assigned
        if (role == Role.DEPT_HEAD && dept != null) {
            dept.setHeadUser(saved);
            deptRepo.save(dept);
        }

        return mapToDTO(saved);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepo.findById(id).map(this::mapToDTO)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public List<UserDTO> getUsersByDepartment(Long deptId) {
        return userRepo.findByDepartmentId(deptId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO approveUser(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setIsApproved(true);
        return mapToDTO(userRepo.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());
        dto.setPassword(null);
        dto.setIsApproved(user.getIsApproved());
        dto.setDepartmentId(user.getDepartment() != null ? user.getDepartment().getId() : null);
        return dto;
    }
}