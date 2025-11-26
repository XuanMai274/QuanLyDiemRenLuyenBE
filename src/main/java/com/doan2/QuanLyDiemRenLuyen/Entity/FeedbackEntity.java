package com.doan2.QuanLyDiemRenLuyen.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="feedback")
@Getter
@Setter
public class FeedbackEntity {
    @Id
    @Column(name="feedback_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_id")
    @SequenceGenerator(name = "feedback_id", sequenceName = "feedback_id_seq", allocationSize = 1)
    private int feedbackId;
    @Column(name="content")
    private String content;
    @Column(name="image")
    private String image;
    @Column(name="gmail")
    private String gmail;
    @ManyToOne()
    @JoinColumn(name="studentId")
    private StudentEntity studentEntity;
    @ManyToOne()
    @JoinColumn(name="conductFormId")
    private ConductFormEntity conductFormEntity;
}
