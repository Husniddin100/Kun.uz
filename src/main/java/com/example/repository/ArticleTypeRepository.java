package com.example.repository;

import com.example.entity.TypeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ArticleTypeRepository extends CrudRepository<TypeEntity, Integer>, PagingAndSortingRepository<TypeEntity,Integer> {
    @Query("update TypeEntity set visible=FALSE where id=?1")
     Optional<TypeEntity>delete(Integer id);
}
