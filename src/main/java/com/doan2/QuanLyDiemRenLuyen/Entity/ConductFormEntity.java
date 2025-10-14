package com.doan2.QuanLyDiemRenLuyen.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="conduct_form")
public class ConductFormEntity {
    @Id
    @Column(name="conduct_form_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conduct_form_id")
    @SequenceGenerator(name = "conduct_form_id", sequenceName = "conduct_form_id_seq", allocationSize = 1)
    private int conduct_form_id;
    @Column(name="student_score")
    private int totalStudentScore;
    @Column(name="class_monitor_score")
    private int classMonitorScore;
    @Column(name="staff_score")
    private int staff_score;
    @Column(name="status")
    private String status;
    @Column(name="create_at")
    private LocalDateTime create_at;
    @Column(name="updated_date")
    private LocalDateTime updated_date;
    @ManyToOne
    @JoinColumn(name="semester_id")
    private SemesterEntity semesterEntity;
    @OneToOne
    @JoinColumn(name="Student_id")
    @JsonBackReference
    private StudentEntity studentEntity;
    @OneToMany(mappedBy ="conductFormEntity" )
    private List<ConductFormDetailEntity> conductFormDetailEntityList;

}
