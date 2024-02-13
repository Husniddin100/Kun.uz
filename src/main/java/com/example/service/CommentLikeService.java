package com.example.service;

import com.example.dto.CommentLikeDTO;
import com.example.entity.ArticleLikeEntity;
import com.example.entity.CommentLikeEntity;
import com.example.enums.LikeStatus;
import com.example.exp.AppBadException;
import com.example.repository.CommentLikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class CommentLikeService {
    @Autowired
    private CommentLikeRepository commentLikeRepository;

    public boolean createLike(CommentLikeDTO dto) {
        Optional<Boolean> optional = commentLikeRepository.findByProfileIdAndCommentId(dto.getProfileId(), dto.getCommentId(), dto.getStatus());
        if (optional.isPresent()) {
            throw new AppBadException("already liked  this article");
        }
        Optional<Boolean> optional1 = commentLikeRepository.findByProfileIdAndComment1Id(dto.getProfileId(), dto.getCommentId());
        if (optional1.isPresent()) {
            remove(dto.getCommentId(),dto.getProfileId());
        }


        CommentLikeEntity entity = new CommentLikeEntity();
        entity.setProfileId(dto.getProfileId());
        entity.setCommentId(dto.getCommentId());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setStatus(dto.getStatus());
        commentLikeRepository.save(entity);
        return true;
    }

    public Boolean remove(Integer commentId, Integer profileId) {
        Optional<Boolean> optional = commentLikeRepository.findByProfileIdAndComment1Id(profileId, commentId);
        if (optional.isEmpty()) {
            throw new AppBadException("the article has not been liked");
        }
        commentLikeRepository.deleteLike(profileId, commentId);
        log.warn(" remove like or dislike");
        return true;
    }
}
