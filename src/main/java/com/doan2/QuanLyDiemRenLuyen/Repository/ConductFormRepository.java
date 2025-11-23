package com.doan2.QuanLyDiemRenLuyen.Repository;

import com.doan2.QuanLyDiemRenLuyen.Entity.ConductFormEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConductFormRepository extends CrudRepository<ConductFormEntity,Integer > {
    @Query("SELECT DISTINCT c FROM ConductFormEntity c WHERE c.studentEntity.studentId = :studentId")
    List<ConductFormEntity> findByStudentEntity_StudentId(@Param("studentId") int studentId);
    ConductFormEntity findByConductFormId(int conductFormId);
    @Query("SELECT cf " +
            "FROM ConductFormEntity cf " +
            "JOIN FETCH cf.studentEntity s " +
            "JOIN FETCH s.classId c " +
            "JOIN FETCH cf.semesterEntity sem " +
            "WHERE c.classId = :classId AND sem.semesterId = :semesterId")
    List<ConductFormEntity> findWithStudentAndClassByClassAndSemester(
            @Param("classId") int classId,
            @Param("semesterId") int semesterId
    );

}
