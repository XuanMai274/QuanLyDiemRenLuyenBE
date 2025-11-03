package com.doan2.QuanLyDiemRenLuyen.ServiceImplement;

import com.doan2.QuanLyDiemRenLuyen.DTO.SemesterDTO;
import com.doan2.QuanLyDiemRenLuyen.Entity.SemesterEntity;
import com.doan2.QuanLyDiemRenLuyen.Mapper.SemesterMapper;
import com.doan2.QuanLyDiemRenLuyen.Repository.SemesterRepository;
import com.doan2.QuanLyDiemRenLuyen.Service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<SemesterDTO> findAll() {
        List<SemesterEntity> semesterEntityList= semesterRepository.findAll();
        List<SemesterDTO> semesterDTOS=new ArrayList<>();
        for(SemesterEntity s:semesterEntityList){
            semesterDTOS.add(semesterMapper.toDTO(s));
        }
        return semesterDTOS;
    }

    @Override
    public List<SemesterDTO> findByIsOpenTrue() {
        try{
            List<SemesterEntity> semesterEntityList=semesterRepository.findOpenSemestersWithinEvaluationPeriod(LocalDate.now());
            if(semesterEntityList!=null){
                List<SemesterDTO> semesterDTOS=new ArrayList<>();
                for(SemesterEntity s:semesterEntityList){
                    semesterDTOS.add(semesterMapper.toDTO(s));
                }
                return semesterDTOS;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return List.of();
    }
}
