package com.doan2.QuanLyDiemRenLuyen.ServiceImplement;

import com.doan2.QuanLyDiemRenLuyen.DTO.ManagerDTO;
import com.doan2.QuanLyDiemRenLuyen.Entity.ManagerEntity;
import com.doan2.QuanLyDiemRenLuyen.Mapper.ManagerMapper;
import com.doan2.QuanLyDiemRenLuyen.Repository.ManagerRepository;
import com.doan2.QuanLyDiemRenLuyen.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImplement implements ManagerService {
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    ManagerMapper managerMapper;
    @Override
    public ManagerDTO findByAccountId(int id) {
        try{
            ManagerEntity managerEntity= managerRepository.findByAccountId(id);
            return managerMapper.toDTO(managerEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
