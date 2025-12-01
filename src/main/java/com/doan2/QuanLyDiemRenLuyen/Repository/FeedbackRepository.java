package com.doan2.QuanLyDiemRenLuyen.Repository;

import com.doan2.QuanLyDiemRenLuyen.Entity.FeedbackEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends CrudRepository<FeedbackEntity,Integer> {
    FeedbackEntity findByFeedbackId(int feedbackId);
    List<FeedbackEntity> findByStudentEntity_studentId(int studentId);
}
