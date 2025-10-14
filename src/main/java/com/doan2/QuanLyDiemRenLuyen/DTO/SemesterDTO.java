package com.doan2.QuanLyDiemRenLuyen.DTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SemesterDTO {
    private int semesterId;
    private String semesterName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String year;
    private List<Integer> conductFormIds; // chỉ lưu danh sách ID để tránh vòng lặp khi mapping
}