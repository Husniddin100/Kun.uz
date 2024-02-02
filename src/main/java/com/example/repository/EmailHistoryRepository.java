package com.example.repository;

import com.example.entity.EmailHistoryEntinty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EmailHistoryRepository extends CrudRepository<EmailHistoryEntinty, Integer> , PagingAndSortingRepository<EmailHistoryEntinty,Integer> {

    Long countByEmailAndCreatedDateBetween(String email, LocalDateTime from, LocalDateTime to);

    @Query("SELECT count (s) from EmailHistoryEntinty s where s.email =?1 and s.createdDate between ?2 and ?3")
    Long countSendEmail(String email, LocalDateTime from, LocalDateTime to);

    List<EmailHistoryEntinty> findByEmail(String email);

    List<EmailHistoryEntinty>findByCreatedDate(LocalDateTime dateTime);


}
