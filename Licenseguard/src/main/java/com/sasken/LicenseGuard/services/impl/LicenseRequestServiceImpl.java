package com.sasken.LicenseGuard.services.impl;

import com.sasken.LicenseGuard.dto.LicenseRequestDTO;
import com.sasken.LicenseGuard.models.LicenseRequest;
import com.sasken.LicenseGuard.models.User;
import com.sasken.LicenseGuard.repository.LicenseRequestRepository;
import com.sasken.LicenseGuard.repository.UserRepository;
import com.sasken.LicenseGuard.services.LicenseRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LicenseRequestServiceImpl implements LicenseRequestService {

    private final LicenseRequestRepository requestRepo;
    private final UserRepository userRepo;

    public LicenseRequestServiceImpl(LicenseRequestRepository requestRepo, UserRepository userRepo) {
        this.requestRepo = requestRepo;
        this.userRepo = userRepo;
    }

    @Override
    public LicenseRequestDTO createRequest(LicenseRequestDTO dto) {
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        LicenseRequest request = new LicenseRequest();
        request.setSoftwareName(dto.getSoftwareName());
        request.setSystemId(dto.getSystemId());
        request.setRequestedBy(user);
        request.setRequestedAt(LocalDate.now());
        request.setStatus("PENDING");

        return mapToDTO(requestRepo.save(request));
    }

    @Override
    public LicenseRequestDTO approveRequest(Long id, String reason) {
        LicenseRequest req = requestRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
        req.setStatus("APPROVED");
        req.setReason(reason);
        return mapToDTO(requestRepo.save(req));
    }

    @Override
    public LicenseRequestDTO rejectRequest(Long id, String reason) {
        LicenseRequest req = requestRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
        req.setStatus("REJECTED");
        req.setReason(reason);
        return mapToDTO(requestRepo.save(req));
    }

    @Override
    public List<LicenseRequestDTO> getAllRequests() {
        return requestRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<LicenseRequestDTO> getRequestsByUser(Long userId) {
        return requestRepo.findByRequestedById(userId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private LicenseRequestDTO mapToDTO(LicenseRequest req) {
        LicenseRequestDTO dto = new LicenseRequestDTO();
        dto.setId(req.getId());
        dto.setSoftwareName(req.getSoftwareName());
        dto.setSystemId(req.getSystemId());
        dto.setUserId(req.getRequestedBy().getId());
        dto.setRequestedAt(req.getRequestedAt());
        dto.setStatus(req.getStatus());
        dto.setReason(req.getReason());
        return dto;
    }
}
