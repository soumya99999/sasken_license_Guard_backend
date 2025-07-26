package com.sasken.LicenseGuard.services;

import com.sasken.LicenseGuard.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO dto);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    List<UserDTO> getUsersByDepartment(Long deptId);
    UserDTO approveUser(Long id);
    void deleteUser(Long id);
}
