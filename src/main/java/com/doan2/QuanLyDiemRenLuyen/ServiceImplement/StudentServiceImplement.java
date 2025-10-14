package com.doan2.QuanLyDiemRenLuyen.ServiceImplement;

import com.doan2.QuanLyDiemRenLuyen.DTO.StudentDTO;
import com.doan2.QuanLyDiemRenLuyen.Entity.StudentEntity;
import com.doan2.QuanLyDiemRenLuyen.Repository.StudentRepository;
import com.doan2.QuanLyDiemRenLuyen.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImplement implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public StudentDTO findByAccountId(int id) {
        StudentEntity student=studentRepository.findByAccountId(id);
        if(student!=null){
            StudentDTO studentDTO=new StudentDTO();
            studentDTO.setFullname(student.getFullname());
            studentDTO.setIsClassMonitor(student.getIsClassMonitor());
            return studentDTO;
        }
        else{
            return  null;
        }
    }

    @Override
    public StudentDTO findByUsername(String username) {
        StudentEntity student=studentRepository.findByUserName(username);
        if(student!=null){
            StudentDTO studentDTO=new StudentDTO();
            studentDTO.setFullname(student.getFullname());
            studentDTO.setIsClassMonitor(student.getIsClassMonitor());
            return studentDTO;
        }
        else{
            return  null;
        }
    }
}
