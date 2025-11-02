package com.doan2.QuanLyDiemRenLuyen.Api;

import com.doan2.QuanLyDiemRenLuyen.DTO.CriteriaTypeDTO;
import com.doan2.QuanLyDiemRenLuyen.Service.CriteriaTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/manager/criteriaType")
public class CriteriaTypeAPI {
    @Autowired
    CriteriaTypeService criteriaTypeService;
    @PostMapping("/manager/criteriaType/add")
    public ResponseEntity<Map<String,Object>> CriteriaTypeAdd(@RequestBody CriteriaTypeDTO criteriaTypeDTO){
        // thêm vào cơ sở dữ liệu
        CriteriaTypeDTO criteriaTypeDTONew=criteriaTypeService.addCriteriaType(criteriaTypeDTO);
        Map<String, Object> response = new HashMap<>();
        if(criteriaTypeDTONew!=null){
            response.put("success", true);
            response.put("criteriaType",criteriaTypeDTONew);
            return ResponseEntity.ok(response);
        }
        else {
            response.put("success", false);
            response.put("criteriaType", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @GetMapping("/criteriaType/getAll")
    public ResponseEntity<List<CriteriaTypeDTO>> getAllSemesters() {
        List<CriteriaTypeDTO> semesters = criteriaTypeService.findAll();
        return ResponseEntity.ok(semesters);
    }
}
