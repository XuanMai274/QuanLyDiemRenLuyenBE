package com.doan2.QuanLyDiemRenLuyen.Api;

import com.doan2.QuanLyDiemRenLuyen.DTO.*;
import com.doan2.QuanLyDiemRenLuyen.Service.CloudinaryService;
import com.doan2.QuanLyDiemRenLuyen.Service.ConductFormService;
import com.doan2.QuanLyDiemRenLuyen.Utill.CustomeUserDetails;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;


@RestController
public class ConductFormAPI {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ConductFormService conductFormService;

    @PostMapping(value = "/conductForm/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createConductForm(
            @RequestPart("conductForm") ConductFormDTO dto,
            @RequestPart(value = "detailFiles", required = false) List<MultipartFile> detailFiles,
            @RequestPart(value = "detailMeta", required = false) List<DetailMetaDTO> detailMetaList
    ) {
       // List<String> uploadedPublicIds = new ArrayList<>();
        try {
            //Kiểm tra đăng nhập và lấy id của người dùng hiện tại
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !(authentication.getPrincipal() instanceof CustomeUserDetails)) {
                return ResponseEntity.status(401).body("Chưa đăng nhập hoặc token không hợp lệ");
            }

            CustomeUserDetails user = (CustomeUserDetails) authentication.getPrincipal();
            int studentId = user.getAccountEntity().getStudentEntity().getStudentId();

            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudentId(studentId);
            dto.setStudent(studentDTO);

            // Nếu không có file thì lưu luôn
            if (detailFiles == null || detailFiles.isEmpty()) {
                ConductFormDTO saved = conductFormService.addConductForm(dto);
                return ResponseEntity.ok(saved);
            }

            // Kiểm tra số lượng file và meta có khớp không
            if (detailMetaList == null || detailMetaList.size() != detailFiles.size()) {
                return ResponseEntity.badRequest().body("Số lượng detailFiles và detailMeta không khớp");
            }

            //Gắn file tương ứng với meta
            for (int i = 0; i < detailFiles.size(); i++) {
                MultipartFile file = detailFiles.get(i);
                DetailMetaDTO meta = detailMetaList.get(i);

                if (meta.getTempId() == null) {
                    return ResponseEntity.badRequest().body("Thiếu tempId cho meta index " + i);
                }

                // Upload ảnh
                Map<String, String> uploadRes = cloudinaryService.uploadImage(file);
                String url = uploadRes.get("url");
                String publicId = uploadRes.get("public_id");
                System.out.println("Public_id nhận được: "+publicId);
              //  uploadedPublicIds.add(publicId);

                // Gán ảnh vào đúng detail
                if (dto.getConductFormDetailList() != null) {
                    for (ConductFormDetailDTO d : dto.getConductFormDetailList()) {
                        if (meta.getTempId().equals(d.getTempId())) {
                            d.setFile(url);
                            d.setPublicId(publicId);
                            break;
                        }
                    }
                }
            }
            // set thời gian hiện tại
            dto.setCreateAt(LocalDateTime.now());
            //Lưu phiếu rèn luyện
            ConductFormDTO savedForm = conductFormService.addConductForm(dto);
            return ResponseEntity.ok(savedForm);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Lỗi xử lý: " + e.getMessage());
        }
    }
    @GetMapping("/conductForm/findAll")
    ResponseEntity<Map<String,Object>> findALlByStudentId(){
        // kiểm tra đăng nhập và lấy id của người dùng hiện tại
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomeUserDetails userDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Tài khoản không tồn tại hoặc đã bị vô hiệu hóa"));
        }
        CustomeUserDetails customeUserDetails=(CustomeUserDetails) userDetails;
        int id=customeUserDetails.getAccountEntity().getStudentEntity().getStudentId();
        List<ConductFormDTO> conductFormDTOS=conductFormService.findByStudentId(id);
        Map<String,Object> response=new HashMap<>();
        if(conductFormDTOS!=null){
            response.put("success", true);
            response.put("conductForms",conductFormDTOS);
            return ResponseEntity.ok(response);
        }
        else {
            response.put("success", false);
            response.put("conductForms", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @GetMapping("/conductForm/Detail/{id}")
    ResponseEntity<Map<String,Object>> findByConductFormId(@PathVariable("id")int id){
        ConductFormDTO conductFormDTO= conductFormService.findByConductFormId(id);
        Map<String,Object> response= new HashMap<>();
        if(conductFormDTO!=null){
            response.put("success", true);
            response.put("conductForm",conductFormDTO);
            return ResponseEntity.ok(response);
        }
        else {
            response.put("success", false);
            response.put("conductForm", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
