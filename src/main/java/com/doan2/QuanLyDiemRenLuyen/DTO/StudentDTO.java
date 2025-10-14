package com.doan2.QuanLyDiemRenLuyen.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private int studentId;
    private String fullname;
    private String gender;
    private String email;
    private String phoneNumber;
    private String status;
    private Boolean isClassMonitor;

    private ClassDTO classEntity;
    private AccountDTO accountEntity;
    private ConductFormDTO conductFormEntity;
}
