package com.doan2.QuanLyDiemRenLuyen.Api;

import com.doan2.QuanLyDiemRenLuyen.DTO.ClassAverageScoreDTO;
import com.doan2.QuanLyDiemRenLuyen.DTO.StudentDTO;
import com.doan2.QuanLyDiemRenLuyen.Service.ConductFormService;
import com.doan2.QuanLyDiemRenLuyen.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class ManagerAPI {
    @Autowired
    StudentService studentService;
    @Autowired
    ConductFormService conductFormService;
    @GetMapping("manager/statistical/{semesterId}/{classId}")
    public Map<String, List<StudentDTO>> findStudentsSubmittedConductForm(@PathVariable("semesterId") int semesterId, @PathVariable("classId") int classId){
        Map<String,List<StudentDTO>> response=new HashMap<>();
        List<StudentDTO> submitted=studentService.findStudentsSubmittedConductForm(semesterId,classId);
        List<StudentDTO> notSubmitted=studentService.findStudentsNotSubmittedConductForm(semesterId,classId);
        response.put("submitted",submitted);
        response.put("notSubmitted",notSubmitted);
        return response;
    }

    @GetMapping("manager/statistical/avgClass/{facultyId}")
    public List<ClassAverageScoreDTO> getAverageScoreByFaculty(@PathVariable("facultyId") int facultyId){
        return conductFormService.getAverageScoreByFaculty(facultyId);
    }


}
