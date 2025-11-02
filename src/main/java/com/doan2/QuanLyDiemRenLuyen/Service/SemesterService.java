package com.doan2.QuanLyDiemRenLuyen.Service;

import com.doan2.QuanLyDiemRenLuyen.DTO.SemesterDTO;

import java.util.List;

public interface SemesterService {
    SemesterDTO addSemester(SemesterDTO semesterDTO);
    List<SemesterDTO> findAll();
}
