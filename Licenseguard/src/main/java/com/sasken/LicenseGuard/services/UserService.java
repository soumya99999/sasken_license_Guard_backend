package com.sasken.LicenseGuard.services;

import com.sasken.LicenseGuard.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO dto);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    List<UserDTO> getUsersByDepartment(Long deptId);

    UserDTO approveUser(Long userId);

    void deleteUser(Long userId);
}
