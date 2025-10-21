package com.doan2.QuanLyDiemRenLuyen.ServiceImplement;

import com.doan2.QuanLyDiemRenLuyen.DTO.ConductFormDTO;
import com.doan2.QuanLyDiemRenLuyen.Entity.ConductFormDetailEntity;
import com.doan2.QuanLyDiemRenLuyen.Entity.ConductFormEntity;
import com.doan2.QuanLyDiemRenLuyen.Mapper.ConductFormMapper;
import com.doan2.QuanLyDiemRenLuyen.Repository.ConductFormRepository;
import com.doan2.QuanLyDiemRenLuyen.Service.ConductFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            // Chuyển DTO sang Entity
            ConductFormEntity conductFormEntity = conductFormMapper.toEntity(conductFormDTO);
            // 2️⃣ Xử lý mối quan hệ với ConductFormDetailEntity
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

        } catch (Exception e) {
            // Ghi log lỗi ra console (hoặc logger)
            System.err.println("Lỗi khi lưu ConductFormEntity: " + e.getMessage());
            e.printStackTrace();

            // Có thể ném lại exception để controller xử lý
            throw new RuntimeException("Không thể thêm phiếu rèn luyện. Vui lòng thử lại sau!", e);
        }
    }
}
