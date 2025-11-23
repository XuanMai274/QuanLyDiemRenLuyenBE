package com.doan2.QuanLyDiemRenLuyen.Service;

import com.doan2.QuanLyDiemRenLuyen.DTO.ConductFormDTO;

import java.util.List;

public interface ConductFormService {
    ConductFormDTO addConductForm(ConductFormDTO conductFormDTO);
    List<ConductFormDTO> findByStudentId(int studentId);
    ConductFormDTO findByConductFormId(int conductFormId);
    List<ConductFormDTO> findByClassAndSemester(int classId,int semester_id);
    ConductFormDTO updateManager(ConductFormDTO conductFormDTO);
}
