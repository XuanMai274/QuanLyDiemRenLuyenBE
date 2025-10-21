package com.doan2.QuanLyDiemRenLuyen.Mapper;

import com.doan2.QuanLyDiemRenLuyen.DTO.CriteriaDTO;
import com.doan2.QuanLyDiemRenLuyen.DTO.CriteriaTypeDTO;
import com.doan2.QuanLyDiemRenLuyen.Entity.CriteriaEntity;
import com.doan2.QuanLyDiemRenLuyen.Entity.CriteriaTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CriteriaTypeMapper {
    // -------------------- Entity → DTO --------------------
    public CriteriaTypeDTO toDTO(CriteriaTypeEntity entity) {
        if (entity == null) return null;

        CriteriaTypeDTO dto = new CriteriaTypeDTO();
        dto.setCriteriaTypeId(entity.getCriteriaTypeId());
        dto.setCriteriaTypeName(entity.getCriteriaTypeName());
        dto.setMaxScore(entity.getMaxScore());

        return dto;
    }

    // -------------------- DTO → Entity --------------------
    public CriteriaTypeEntity toEntity(CriteriaTypeDTO dto) {
        if (dto == null) return null;

        CriteriaTypeEntity entity = new CriteriaTypeEntity();
        entity.setCriteriaTypeId(dto.getCriteriaTypeId());
        entity.setCriteriaTypeName(dto.getCriteriaTypeName());
        entity.setMaxScore(dto.getMaxScore());
        return entity;
    }

}
