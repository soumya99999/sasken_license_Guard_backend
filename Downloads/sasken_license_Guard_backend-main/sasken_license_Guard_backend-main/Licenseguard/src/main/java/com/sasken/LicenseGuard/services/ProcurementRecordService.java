package com.sasken.LicenseGuard.services;

import com.sasken.LicenseGuard.dto.ProcurementRecordDTO;

import java.util.List;

public interface ProcurementRecordService {
    ProcurementRecordDTO createProcurementRecord(ProcurementRecordDTO dto);
    List<ProcurementRecordDTO> getAllProcurementRecords();
    ProcurementRecordDTO getProcurementById(Long poHeaderId);
}
