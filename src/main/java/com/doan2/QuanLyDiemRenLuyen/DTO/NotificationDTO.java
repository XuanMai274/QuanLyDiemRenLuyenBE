package com.doan2.QuanLyDiemRenLuyen.DTO;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationDTO {
    private int notificationId;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    // Quan hệ
    private ManagerDTO managerEntity;
}
