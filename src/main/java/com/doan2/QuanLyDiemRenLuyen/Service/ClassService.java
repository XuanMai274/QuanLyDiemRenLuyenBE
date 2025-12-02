package com.doan2.QuanLyDiemRenLuyen.Service;

import com.doan2.QuanLyDiemRenLuyen.DTO.ClassDTO;

import java.util.List;

public interface ClassService {
    List<ClassDTO> findAll();
    List<ClassDTO> findByFacultyId(int facultyId);

}
