package com.doan2.QuanLyDiemRenLuyen.Repository;

import com.doan2.QuanLyDiemRenLuyen.Entity.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity,Integer> {
    @Query("select s from StudentEntity s where s.accountEntity.accountId = :accountId ")
    StudentEntity findByAccountId(@Param("accountId") int accountId);
    @Query("select s from StudentEntity s where s.accountEntity.username = :username ")
    StudentEntity findByUserName(@Param("username") String username);
    StudentEntity findByStudentId(int studentId);
}
