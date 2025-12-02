package com.doan2.QuanLyDiemRenLuyen.Api;

import com.doan2.QuanLyDiemRenLuyen.DTO.ClassDTO;
import com.doan2.QuanLyDiemRenLuyen.Service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClassAPI {
    @Autowired
    ClassService classService;
    @GetMapping("/manager/class/getAll")
    public ResponseEntity<List<ClassDTO>> findAll(){
        List<ClassDTO> classDTOS=classService.findAll();
        if(classDTOS!=null){
            return ResponseEntity.ok(classDTOS);
        }
        return null;
    }
    @GetMapping("/manager/class/faculty/{facultyId}")
    public ResponseEntity<List<ClassDTO>> findByFaculty(@PathVariable("facultyId") int facultyId){
        List<ClassDTO> classDTOS= classService.findByFacultyId(facultyId);
        if(classDTOS!=null){
            return ResponseEntity.ok(classDTOS);
        }
        return null;
    }
}
