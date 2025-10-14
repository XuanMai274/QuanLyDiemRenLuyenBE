package com.doan2.QuanLyDiemRenLuyen.Service;

import com.doan2.QuanLyDiemRenLuyen.DTO.StudentDTO;

public interface StudentService {
    StudentDTO findByAccountId(int id);
    StudentDTO findByUsername(String username);

}
