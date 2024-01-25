package com.example.repository;

import com.example.entity.ArticleTypeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ArticleTypeRepository extends CrudRepository<ArticleTypeEntity, Integer>, PagingAndSortingRepository<ArticleTypeEntity,Integer> {
    @Query("update ArticleTypeEntity set visible=FALSE where id=?1")
     Optional<ArticleTypeEntity>delete(Integer id);
}
