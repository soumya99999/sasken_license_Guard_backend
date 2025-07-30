package com.sasken.LicenseGuard.services.impl;

import com.sasken.LicenseGuard.dto.LicenseAssignmentDTO;
import com.sasken.LicenseGuard.models.LicenseAssignment;
import com.sasken.LicenseGuard.models.LicenseInventory;
import com.sasken.LicenseGuard.models.User;
import com.sasken.LicenseGuard.repository.LicenseAssignmentRepository;
import com.sasken.LicenseGuard.repository.LicenseInventoryRepository;
import com.sasken.LicenseGuard.repository.UserRepository;
import com.sasken.LicenseGuard.services.LicenseAssignmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LicenseAssignmentServiceImpl implements LicenseAssignmentService {

    private final LicenseAssignmentRepository assignmentRepo;
    private final LicenseInventoryRepository licenseRepo;
    private final UserRepository userRepo;

    public LicenseAssignmentServiceImpl(
            LicenseAssignmentRepository assignmentRepo,
            LicenseInventoryRepository licenseRepo,
            UserRepository userRepo) {
        this.assignmentRepo = assignmentRepo;
        this.licenseRepo = licenseRepo;
        this.userRepo = userRepo;
    }

    @Override
    public LicenseAssignmentDTO issueLicenseToDeptHead(LicenseAssignmentDTO dto) {
        return assign(dto, true); // reduce quantity
    }

    @Override
    public LicenseAssignmentDTO assignLicenseToUser(LicenseAssignmentDTO dto) {
        return assign(dto, false); // don't reduce quantity
    }

    private LicenseAssignmentDTO assign(LicenseAssignmentDTO dto, boolean reduceInventory) {
        LicenseInventory license = licenseRepo.findById(dto.getLicenseInventoryId())
                .orElseThrow(() -> new IllegalArgumentException("License not found"));

        User toUser = userRepo.findById(dto.getAssignedToUserId())
                .orElseThrow(() -> new IllegalArgumentException("Assigned-to User not found"));

        User byUser = userRepo.findById(dto.getAssignedByUserId())
                .orElseThrow(() -> new IllegalArgumentException("Assigned-by User not found"));

        // Check and reduce if it's an admin issuing
        if (reduceInventory) {
            if (license.getAvailableQuantity() < dto.getAssignedQuantity()) {
                throw new IllegalStateException("Not enough available licenses.");
            }
            license.setAvailableQuantity(license.getAvailableQuantity() - dto.getAssignedQuantity());
            licenseRepo.save(license);
        }

        LicenseAssignment assignment = new LicenseAssignment();
        assignment.setAssignedQuantity(dto.getAssignedQuantity());
        assignment.setAssignedAt(LocalDate.now());
        assignment.setExpiresAt(dto.getExpiresAt());
        assignment.setLicenseInventory(license);
        assignment.setAssignedTo(toUser);
        assignment.setAssignedBy(byUser);

        return mapToDTO(assignmentRepo.save(assignment));
    }

    @Override
    public List<LicenseAssignmentDTO> getAssignmentsByUser(Long userId) {
        return assignmentRepo.findByAssignedToId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LicenseAssignmentDTO> getAllAssignments() {
        return assignmentRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private LicenseAssignmentDTO mapToDTO(LicenseAssignment a) {
        LicenseAssignmentDTO dto = new LicenseAssignmentDTO();
        dto.setId(a.getId());
        dto.setAssignedQuantity(a.getAssignedQuantity());
        dto.setAssignedAt(a.getAssignedAt());
        dto.setExpiresAt(a.getExpiresAt());
        dto.setLicenseInventoryId(a.getLicenseInventory().getId());
        dto.setAssignedToUserId(a.getAssignedTo().getId());
        dto.setAssignedByUserId(a.getAssignedBy().getId());
        return dto;
    }
}
