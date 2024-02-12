package com.example.repository;

import com.example.entity.ArticleLikeEntity;
import com.example.enums.LikeStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Optional;

public interface ArticleLikeRepository extends CrudRepository<ArticleLikeEntity,Integer> {

    @Query("from ArticleLikeEntity where profileId=?1 and articleId=?2 and status=?3")
    Optional<Boolean> findByProfileIdAndArticleId(Integer profileId, String articleId, LikeStatus status);
    @Query("from ArticleLikeEntity where profileId=?1 and articleId=?2")
    Optional<Boolean> findByProfileIdAndArticleId1(Integer profileId, String articleId);
    @Modifying
    @Transactional
    @Query("delete from ArticleLikeEntity where profileId=?1 and articleId=?2")
    void deleteLike(Integer profileId, String articleId);



}
