package com.sasken.LicenseGuard.repository;

import com.sasken.LicenseGuard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByDepartmentId(Long departmentId);
}
