package com.sasken.LicenseGuard.services.impl;

import com.sasken.LicenseGuard.dto.UserDTO;
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
    private final DepartmentRepository departmentRepo;

    public UserServiceImpl(UserRepository userRepo, DepartmentRepository departmentRepo) {
        this.userRepo = userRepo;
        this.departmentRepo = departmentRepo;
    }

    @Override
    public UserDTO createUser(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setIsApproved(false); // default state

        if (dto.getDepartmentId() != null) {
            Department dept = departmentRepo.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException("Department not found"));
            user.setDepartment(dept);
        }

        return mapToDTO(userRepo.save(user));
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepo.findById(id).map(this::mapToDTO)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getUsersByDepartment(Long deptId) {
        return userRepo.findByDepartmentId(deptId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO approveUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setIsApproved(true);
        return mapToDTO(userRepo.save(user));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }

    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(null); // mask it
        dto.setRole(user.getRole());
        dto.setIsApproved(user.getIsApproved());
        dto.setDepartmentId(user.getDepartment() != null ? user.getDepartment().getId() : null);
        return dto;
    }
}
