package com.example.repository;

import com.example.entity.ArticleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ArticleRepository extends CrudRepository<ArticleEntity, String> {
    @Query("from ArticleEntity where id=?1")
    Optional<ArticleEntity> getById(String id);

    @Query("update ArticleEntity set visible=false where id=?1")
    void deleteById(String id);

    @Query("update ArticleEntity set status='Published' where id=?1")
    public Boolean changeStatus(String id);


}
