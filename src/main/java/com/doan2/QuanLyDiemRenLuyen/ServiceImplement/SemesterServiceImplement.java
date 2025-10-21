package com.doan2.QuanLyDiemRenLuyen.ServiceImplement;

import com.doan2.QuanLyDiemRenLuyen.DTO.SemesterDTO;
import com.doan2.QuanLyDiemRenLuyen.Entity.SemesterEntity;
import com.doan2.QuanLyDiemRenLuyen.Mapper.SemesterMapper;
import com.doan2.QuanLyDiemRenLuyen.Repository.SemesterRepository;
import com.doan2.QuanLyDiemRenLuyen.Service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SemesterServiceImplement implements SemesterService {
    @Autowired
    SemesterRepository semesterRepository;
    @Autowired
    SemesterMapper semesterMapper;
    @Override
    public SemesterDTO addSemester(SemesterDTO semesterDTO) {
        try {
            // chuyển DTO thành Entity
            SemesterEntity semesterEntity=semesterMapper.toEntity(semesterDTO);
            // lưu vao db
            semesterRepository.save(semesterEntity);
            return semesterMapper.toDTO(semesterEntity);
        }catch (Exception e){
            System.err.println("Lỗi khi lưu SemesterEntity: " + e.getMessage());
            // Có thể ném lại exception để controller xử lý
            throw new RuntimeException("Không thể thêm hoc ki. Vui lòng thử lại sau!", e);
        }
    }
}
