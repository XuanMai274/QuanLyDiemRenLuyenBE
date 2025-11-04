package com.doan2.QuanLyDiemRenLuyen.Service;

import com.doan2.QuanLyDiemRenLuyen.DTO.ManagerDTO;

public interface ManagerService {
    ManagerDTO findByAccountId(int id);
}
