package com.doan2.QuanLyDiemRenLuyen.Api;

import com.doan2.QuanLyDiemRenLuyen.DTO.CriteriaDTO;
import com.doan2.QuanLyDiemRenLuyen.DTO.CriteriaTypeDTO;
import com.doan2.QuanLyDiemRenLuyen.Service.CriteriaService;
import com.doan2.QuanLyDiemRenLuyen.Service.CriteriaTypeService;
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
@RequestMapping("/manager/criteria")
public class CriteriaAPI {
    @Autowired
    CriteriaService criteriaService;
    @PostMapping("/add")
    public ResponseEntity<Map<String,Object>> CriteriaTypeAdd(@RequestBody CriteriaDTO criteriaDTO){
        // thêm vào cơ sở dữ liệu
        CriteriaDTO criteriaDTONew=criteriaService.addCriteria(criteriaDTO);
        Map<String, Object> response = new HashMap<>();
        if(criteriaDTONew!=null){
            response.put("success", true);
            response.put("criteria",criteriaDTONew);
            return ResponseEntity.ok(response);
        }
        else {
            response.put("success", false);
            response.put("criteria", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
