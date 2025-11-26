package com.doan2.QuanLyDiemRenLuyen.Mapper;

import com.doan2.QuanLyDiemRenLuyen.DTO.ConductFormDTO;
import com.doan2.QuanLyDiemRenLuyen.DTO.FeedbackDTO;
import com.doan2.QuanLyDiemRenLuyen.DTO.StudentDTO;
import com.doan2.QuanLyDiemRenLuyen.Entity.ConductFormEntity;
import com.doan2.QuanLyDiemRenLuyen.Entity.FeedbackEntity;
import com.doan2.QuanLyDiemRenLuyen.Entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMapper {
    // Chuyển từ Entity sang DTO
    public  FeedbackDTO toDTO(FeedbackEntity entity) {
        if (entity == null) return null;

        FeedbackDTO dto = new FeedbackDTO();
        dto.setFeedbackId(entity.getFeedbackId());
        dto.setContent(entity.getContent());
        dto.setImage(entity.getImage());
        dto.setGmail(entity.getGmail());
        StudentEntity studentEntity = entity.getStudentEntity();
        if (studentEntity != null) {
            StudentDTO studentDTO = getStudentDTO(studentEntity);
            // Không map class/account list để tránh vòng lặp
            dto.setStudentDTO(studentDTO);
        }
        // Mapping conductForm
        ConductFormEntity conductFormEntity = entity.getConductFormEntity();
        if (conductFormEntity != null) {
            ConductFormDTO conductFormDTO = new ConductFormDTO();
            conductFormDTO.setConductFormId(conductFormEntity.getConductFormId());
            conductFormDTO.setTotalStudentScore(conductFormEntity.getTotalStudentScore());
            conductFormDTO.setClassMonitorScore(conductFormEntity.getClassMonitorScore());
            conductFormDTO.setStaffScore(conductFormEntity.getStaff_score());
            conductFormDTO.setStatus(conductFormEntity.getStatus());
            conductFormDTO.setCreateAt(conductFormEntity.getCreate_at());
            conductFormDTO.setUpdatedDate(conductFormEntity.getUpdated_date());
            // Không map danh sách chi tiết và feedbackEntities để tránh vòng lặp
            dto.setConductFormDTO(conductFormDTO);
        }
        return dto;
    }

    private static StudentDTO getStudentDTO(StudentEntity studentEntity) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(studentEntity.getStudentId());
        studentDTO.setFullname(studentEntity.getFullname());
        studentDTO.setGender(studentEntity.getGender());
        studentDTO.setEmail(studentEntity.getEmail());
        studentDTO.setPhoneNumber(studentEntity.getPhoneNumber());
        studentDTO.setStatus(studentEntity.getStatus());
        studentDTO.setIsClassMonitor(studentEntity.getIsClassMonitor());
        return studentDTO;
    }

    // Chuyển từ DTO sang Entity
    public static FeedbackEntity toEntity(FeedbackDTO dto, StudentEntity student, ConductFormEntity conductForm) {
        if (dto == null) return null;

        FeedbackEntity entity = new FeedbackEntity();
        entity.setFeedbackId(dto.getFeedbackId());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());
        entity.setGmail(dto.getGmail());
        entity.setStudentEntity(student);
        entity.setConductFormEntity(conductForm);
        // Mapping student
        StudentDTO studentDTO = dto.getStudentDTO();
        if (studentDTO != null) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setStudentId(studentDTO.getStudentId());
            studentEntity.setFullname(studentDTO.getFullname());
            studentEntity.setGender(studentDTO.getGender());
            studentEntity.setEmail(studentDTO.getEmail());
            studentEntity.setPhoneNumber(studentDTO.getPhoneNumber());
            studentEntity.setStatus(studentDTO.getStatus());
            studentEntity.setIsClassMonitor(studentDTO.getIsClassMonitor());
            entity.setStudentEntity(studentEntity);
        }

        // Mapping conductForm
        ConductFormDTO conductFormDTO = dto.getConductFormDTO();
        if (conductFormDTO != null) {
            ConductFormEntity conductFormEntity = new ConductFormEntity();
            conductFormEntity.setConductFormId(conductFormDTO.getConductFormId());
            conductFormEntity.setTotalStudentScore(conductFormDTO.getTotalStudentScore());
            conductFormEntity.setClassMonitorScore(conductFormDTO.getClassMonitorScore());
            conductFormEntity.setStaff_score(conductFormDTO.getStaffScore());
            conductFormEntity.setStatus(conductFormDTO.getStatus());
            conductFormEntity.setCreate_at(conductFormDTO.getCreateAt());
            conductFormEntity.setUpdated_date(conductFormDTO.getUpdatedDate());
            entity.setConductFormEntity(conductFormEntity);
        }
        return entity;
    }

    // Cập nhật Entity từ DTO (update existing entity)
    public static void updateEntity(FeedbackEntity entity, FeedbackDTO dto, StudentEntity student, ConductFormEntity conductForm) {
        if (entity == null || dto == null) return;

        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());
        entity.setGmail(dto.getGmail());
        entity.setStudentEntity(student);
        entity.setConductFormEntity(conductForm);
    }
}
