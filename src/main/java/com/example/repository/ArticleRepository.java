package com.example.repository;

import com.example.entity.ArticleEntity;
import com.example.enums.ArticleStatus;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<ArticleEntity, String> {


    @Modifying
    @Query("update ArticleEntity set visible=false where id=?1")
    void deleteById(@NotNull String id);

    @Modifying
    @Transactional
    @Query("update ArticleEntity set status=?2 where id=?1")
    Integer changeStatus(@NotNull String id, ArticleStatus status);

    @Query("select a from ArticleEntity a where 1=1 order by a.createdDate desc limit 5")
    List<ArticleEntity> getTypeArticleList();

    @Modifying
    @Transactional
    @Query("select a from ArticleEntity a where a.id!=?1 order by a.id desc limit 8")
    List<ArticleEntity> getLast8(String[] id);


}
