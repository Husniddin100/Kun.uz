package com.example.repository;

import com.example.entity.SmsHistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SmsHistoryRepository extends CrudRepository<SmsHistoryEntity, Integer> , PagingAndSortingRepository<SmsHistoryEntity,Integer> {
    List<SmsHistoryEntity> findByPhone(String phone);
    List<SmsHistoryEntity> findByCreatedDate(LocalDateTime date);
}
