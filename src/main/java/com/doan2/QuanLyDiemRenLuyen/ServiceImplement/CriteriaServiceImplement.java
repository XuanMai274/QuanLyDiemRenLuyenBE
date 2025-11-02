package com.doan2.QuanLyDiemRenLuyen.ServiceImplement;

import com.doan2.QuanLyDiemRenLuyen.DTO.CriteriaDTO;
import com.doan2.QuanLyDiemRenLuyen.Entity.CriteriaEntity;
import com.doan2.QuanLyDiemRenLuyen.Entity.CriteriaTypeEntity;
import com.doan2.QuanLyDiemRenLuyen.Mapper.CriteriaMapper;
import com.doan2.QuanLyDiemRenLuyen.Repository.CriteriaRepository;
import com.doan2.QuanLyDiemRenLuyen.Service.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CriteriaServiceImplement implements CriteriaService {
    @Autowired
    CriteriaRepository criteriaRepository;
    @Autowired
    CriteriaMapper criteriaMapper;
    @Override
    public CriteriaDTO addCriteria(CriteriaDTO criteriaDTO) {
        try {
            // chuyển sang entity
            CriteriaEntity criteriaEntity=criteriaMapper.toEntity(criteriaDTO);
            // lưu vào Db
            CriteriaEntity savedEntity=criteriaRepository.save(criteriaEntity);
            // chuyển ngược lại về DTO
            return criteriaMapper.toDTO(savedEntity);
        } catch (Exception e) {
            System.err.println("Lỗi khi lưu CriteriaEntity: " + e.getMessage());
            // Có thể ném lại exception để controller xử lý
            throw new RuntimeException("Không thể thêm tiêu chí. Vui lòng thử lại sau!", e);
        }
    }

    @Override
    public List<CriteriaDTO> findAll() {
        List<CriteriaEntity> criteriaEntityList=criteriaRepository.findAll();
        List<CriteriaDTO> criteriaDTOS=new ArrayList<>();
        for (CriteriaEntity c : criteriaEntityList) {
            criteriaDTOS.add(criteriaMapper.toDTO(c));
        }
        return criteriaDTOS;
    }
}
