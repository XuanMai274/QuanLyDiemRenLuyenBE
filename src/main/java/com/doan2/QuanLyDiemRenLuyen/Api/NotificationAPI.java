package com.doan2.QuanLyDiemRenLuyen.Api;

import com.doan2.QuanLyDiemRenLuyen.DTO.ManagerDTO;
import com.doan2.QuanLyDiemRenLuyen.DTO.NotificationDTO;
import com.doan2.QuanLyDiemRenLuyen.Service.NotificationService;
import com.doan2.QuanLyDiemRenLuyen.Utill.CustomeUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class NotificationAPI {
    @Autowired
    NotificationService notificationService;
    // lấy lên tất cả các thông báo mà người quản lý hiện tại đã tạo
    @GetMapping("/manager/notification/getAll")
    public ResponseEntity<List<NotificationDTO>> getAllNotificationByManagerId (){
        //Kiểm tra đăng nhập và lấy id của người dùng hiện tại
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomeUserDetails)) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
        CustomeUserDetails user = (CustomeUserDetails) authentication.getPrincipal();
        int managerId= user.getAccountEntity().getManagerEntity().getManagerId();
        List<NotificationDTO> notificationDTOS=notificationService.getAllNotificationByManagerId(managerId);
        if(notificationDTOS!=null){
            return ResponseEntity.ok(notificationDTOS);
        }
        return null;
    }
    // lay tất cả thông báo cho sinh viên
    @GetMapping("/notification/getAll")
    public  ResponseEntity<List<NotificationDTO>> getAll(){
        List<NotificationDTO> notificationDTOS=notificationService.findAll();
        if(notificationDTOS!=null){
            return ResponseEntity.ok(notificationDTOS);
        }
        return null;

    }
    @PostMapping("/manager/notification/add")
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notificationDTO){
        try {
            //Kiểm tra đăng nhập và lấy id của người dùng hiện tại
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !(authentication.getPrincipal() instanceof CustomeUserDetails)) {
                return ResponseEntity.badRequest().body((NotificationDTO) Collections.emptyList());
            }
            CustomeUserDetails user = (CustomeUserDetails) authentication.getPrincipal();
            int managerId= user.getAccountEntity().getManagerEntity().getManagerId();
            ManagerDTO managerDTO=new ManagerDTO();
            managerDTO.setManagerId(managerId);
            notificationDTO.setManagerEntity(managerDTO);
            NotificationDTO notificationDTO1=notificationService.addNotification(notificationDTO);
            if(notificationDTO1!=null){
                return ResponseEntity.ok(notificationDTO1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @PostMapping("/manager/notification/update")
    public ResponseEntity<NotificationDTO> updateNotification(@RequestBody NotificationDTO notificationDTO){
        try {
            NotificationDTO notificationDTO1=notificationService.addNotification(notificationDTO);
            if(notificationDTO1!=null){
                return ResponseEntity.ok(notificationDTO1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
