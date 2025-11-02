package com.doan2.QuanLyDiemRenLuyen.Repository;

import com.doan2.QuanLyDiemRenLuyen.Entity.CriteriaTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriteriaTypeRepository extends CrudRepository<CriteriaTypeEntity,Integer> {
    @NonNull
    List<CriteriaTypeEntity> findAll();
}
