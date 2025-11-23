package com.doan2.QuanLyDiemRenLuyen.ServiceImplement;

import com.doan2.QuanLyDiemRenLuyen.DTO.ConductFormDTO;
import com.doan2.QuanLyDiemRenLuyen.DTO.ConductFormDetailDTO;
import com.doan2.QuanLyDiemRenLuyen.Entity.ConductFormDetailEntity;
import com.doan2.QuanLyDiemRenLuyen.Entity.ConductFormEntity;
import com.doan2.QuanLyDiemRenLuyen.Mapper.ConductFormMapper;
import com.doan2.QuanLyDiemRenLuyen.Repository.ConductFormRepository;
import com.doan2.QuanLyDiemRenLuyen.Service.ConductFormService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConductFormServiceImplement implements ConductFormService {
    @Autowired
    ConductFormRepository conductFormRepository;
    @Autowired
    ConductFormMapper conductFormMapper;
    // mở một Transaction để vừa có thể thêm vào ConductForm vừa có tể thêm vào conductFormDetail
    @Transactional
    @Override
    public ConductFormDTO addConductForm(ConductFormDTO conductFormDTO) {
        try {
            if(conductFormDTO.getConductFormId()!=0){
                ConductFormEntity conductFormEntity=conductFormRepository.findByConductFormId(conductFormDTO.getConductFormId());
                if(conductFormEntity!=null){
                    conductFormEntity = conductFormMapper.toEntity(conductFormDTO);
                    if (conductFormEntity.getConductFormDetailEntityList() != null) {
                        for (ConductFormDetailEntity detailEntity : conductFormEntity.getConductFormDetailEntityList()) {
                            // Gán ngược mối quan hệ cha cho từng detail
                            detailEntity.setConductFormEntity(conductFormEntity);
                        }
                    }
                    // Lưu xuống database
                    ConductFormEntity savedEntity = conductFormRepository.save(conductFormEntity);

                    // Trả về DTO (từ entity đã được lưu)
                    return conductFormMapper.toDTO(savedEntity);
                }
            }else{
                // Chuyển DTO sang Entity
                ConductFormEntity conductFormEntity = conductFormMapper.toEntity(conductFormDTO);
                // Xử lý mối quan hệ với ConductFormDetailEntity
                if (conductFormEntity.getConductFormDetailEntityList() != null) {
                    for (ConductFormDetailEntity detailEntity : conductFormEntity.getConductFormDetailEntityList()) {
                        // Gán ngược mối quan hệ cha cho từng detail
                        detailEntity.setConductFormEntity(conductFormEntity);
                    }
                }
                // Lưu xuống database
                ConductFormEntity savedEntity = conductFormRepository.save(conductFormEntity);

                // Trả về DTO (từ entity đã được lưu)
                return conductFormMapper.toDTO(savedEntity);

            }
        } catch (Exception e) {
            // Ghi log lỗi ra console (hoặc logger)
            System.err.println("Lỗi khi lưu ConductFormEntity: " + e.getMessage());
            e.printStackTrace();

            // Có thể ném lại exception để controller xử lý
            throw new RuntimeException("Không thể thêm phiếu rèn luyện. Vui lòng thử lại sau!", e);
        }
        return null;
    }

    @Override
    public List<ConductFormDTO> findByStudentId(int studentId) {
       try {
           List<ConductFormDTO> conductFormDTOS=new ArrayList<>();
           List<ConductFormEntity> conductFormEntities=conductFormRepository.findByStudentEntity_StudentId(studentId);
           for(ConductFormEntity c: conductFormEntities){
               conductFormDTOS.add(conductFormMapper.toDTO(c));
           }
           return conductFormDTOS;
       } catch (Exception e) {
           // Ghi log lỗi ra console (hoặc logger)
           System.err.println("Lỗi khi lấy danh sách ConductForm: " + e.getMessage());
           e.printStackTrace();

           // Có thể ném lại exception để controller xử lý
           throw new RuntimeException("Không thể lâ danh sách phiếu rèn luyện. Vui lòng thử lại sau!", e);
       }
    }

    @Override
    public ConductFormDTO findByConductFormId(int conductFormId) {
        var entity = conductFormRepository.findByConductFormId(conductFormId);

        if (entity == null) {
            throw new EntityNotFoundException("Không tìm thấy phiếu rèn luyện với ID: " + conductFormId);
        }

        try {
            return conductFormMapper.toDTO(entity);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi chuyển đổi ConductFormEntity sang DTO", e);
        }
    }

    @Override
    public List<ConductFormDTO> findByClassAndSemester(int classId, int semester_id) {
        List<ConductFormEntity> conductFormEntityList=conductFormRepository.findWithStudentAndClassByClassAndSemester(classId,semester_id);
        if(conductFormEntityList!=null){
            List<ConductFormDTO> conductFormDTOList=new ArrayList<>();
            for(ConductFormEntity c:conductFormEntityList){
                conductFormDTOList.add(conductFormMapper.toDTO(c));
            }
            return conductFormDTOList;
        }
        return List.of();
    }

    @Override
    public ConductFormDTO updateManager(ConductFormDTO conductFormDTO) {
        try {
            ConductFormEntity conductFormEntity=conductFormRepository.findByConductFormId(conductFormDTO.getConductFormId());
            if (conductFormEntity.getConductFormDetailEntityList() != null) {
                for (ConductFormDetailEntity detailEntity : conductFormEntity.getConductFormDetailEntityList()) {
                    ConductFormDetailDTO dto = conductFormDTO.getConductFormDetailList()
                            .stream()
                            .filter(d -> d.getConductFormDetailId() == detailEntity.getConductFormDetailId())
                            .findFirst()
                            .orElse(null);

                    if (dto != null) {
                        detailEntity.setStaff_score(dto.getStaffScore());
                    }
                }
                conductFormEntity.setStatus("APPROVE");
                conductFormEntity.setStaff_score(conductFormDTO.getStaffScore());
                conductFormEntity.setUpdated_date(LocalDateTime.now());
                conductFormRepository.save(conductFormEntity);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
