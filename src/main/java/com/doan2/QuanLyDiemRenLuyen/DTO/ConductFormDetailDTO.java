package com.doan2.QuanLyDiemRenLuyen.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConductFormDetailDTO {
    private int conductFormDetailId;
    private int selfScore;
    private int classMonitorScore;
    private int staffScore;
    private String comment;
    private String file;

    // Quan hệ
    private ConductFormDTO conductFormEntity;
    private CriteriaDTO criteriaEntity;
}