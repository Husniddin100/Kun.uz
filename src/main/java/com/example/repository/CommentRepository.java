package com.example.repository;

import com.example.entity.CommentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {
    @Query("select count(c)from CommentEntity c where c.profileId=?1 and c.articleId=?2")
    Optional<Boolean> findByProfileIdAndArticleId(Integer profileId, String articleId);

    @Modifying
    @Transactional
    @Query("delete from CommentEntity where profileId=?1 and articleId=?2")
    void deleteLike(Integer profileId, String articleId);

   @Modifying
   @Transactional
    @Query("update CommentEntity set visible=FALSE where id=?1")
    void visibleFalse(Integer id);

    @Query("select c from CommentEntity c  where c.articleId=?1")
    Iterable<CommentEntity> getAll(String id);
}
