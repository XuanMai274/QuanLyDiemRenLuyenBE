package com.doan2.QuanLyDiemRenLuyen.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackDTO {
    private int feedbackId;
    private String content;
    private String image;
    private String gmail;
    private StudentDTO studentDTO;
    private ConductFormDTO conductFormDTO;
}
