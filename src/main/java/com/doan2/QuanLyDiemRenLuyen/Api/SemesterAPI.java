package com.doan2.QuanLyDiemRenLuyen.Api;

import com.doan2.QuanLyDiemRenLuyen.DTO.SemesterDTO;
import com.doan2.QuanLyDiemRenLuyen.Service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/manager/semester")
public class SemesterAPI {
    @Autowired
    SemesterService semesterService;
    @PostMapping("/manager/semester/add")
    public ResponseEntity<Map<String,Object>> addSemester(@RequestBody SemesterDTO semesterDTO){
        // thêm vào csdl
        SemesterDTO semesterDTONew=semesterService.addSemester(semesterDTO);
        Map<String, Object> response = new HashMap<>();
        if(semesterDTONew!=null){
            response.put("success", true);
            response.put("semester",semesterDTONew);
            return ResponseEntity.ok(response);
        }
        else {
            response.put("success", false);
            response.put("semester", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @GetMapping("/semester/getAll")
    public ResponseEntity<List<SemesterDTO>> getAllSemesters() {
        List<SemesterDTO> semesters = semesterService.findAll();
        return ResponseEntity.ok(semesters);
    }
    // hàm sửa học kì
    @PostMapping("/manager/semester/update")
    public ResponseEntity<SemesterDTO> update(@RequestBody SemesterDTO semesterDTO){
        try{
            SemesterDTO semesterDTO1=semesterService.addSemester(semesterDTO);
            return ResponseEntity.ok(semesterDTO1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @GetMapping("/semester/Open")
    public ResponseEntity<List<SemesterDTO>> findByIsOpenTrue(){
        List<SemesterDTO> semesterDTOS=semesterService.findByIsOpenTrue();
        return ResponseEntity.ok(semesterDTOS);
    }
    @PostMapping("manager/createBatch")
    public ResponseEntity<SemesterDTO> CreateBatch(@RequestBody SemesterDTO semesterDTO){
        SemesterDTO semester=semesterService.CreateBatch(semesterDTO);
        return ResponseEntity.ok(semester);
    }
    @GetMapping("/manager/semester/opened")
    public ResponseEntity<List<SemesterDTO>> findSemesterOpened(){
        List<SemesterDTO> semesterDTOList=semesterService.findSemesterOpened();
        return ResponseEntity.ok(semesterDTOList);
    }
    // tức là học kì chưa từng mở
    @GetMapping("/manager/semester/available")
    public ResponseEntity<List<SemesterDTO>> availableSemesters(){
        List<SemesterDTO> semesterDTOList=semesterService.availableSemesters();
        return ResponseEntity.ok(semesterDTOList);
    }

}
