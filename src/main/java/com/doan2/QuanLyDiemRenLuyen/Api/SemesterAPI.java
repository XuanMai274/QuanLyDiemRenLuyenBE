package com.doan2.QuanLyDiemRenLuyen.Api;

import com.doan2.QuanLyDiemRenLuyen.DTO.SemesterDTO;
import com.doan2.QuanLyDiemRenLuyen.Service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/manager/semester")
public class SemesterAPI {
    @Autowired
    SemesterService semesterService;
    @PostMapping("/add")
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
}
