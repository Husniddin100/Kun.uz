package com.example.repository;

import com.example.entity.CommentLikeEntity;
import com.example.enums.LikeStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentLikeRepository extends CrudRepository<CommentLikeEntity,Integer> {
    @Query("from CommentLikeEntity where profileId=?1 and commentId=?2 and status=?3")
    Optional<Boolean> findByProfileIdAndCommentId(Integer profileId, Integer commentId, LikeStatus status);
    @Query("from CommentLikeEntity where profileId=?1 and commentId=?2")
    Optional<Boolean> findByProfileIdAndComment1Id(Integer profileId, Integer commentId);
    @Modifying
    @Transactional
    @Query("delete from CommentLikeEntity where profileId=?1 and commentId=?2")
    void deleteLike(Integer profileId, Integer commentId);

}
