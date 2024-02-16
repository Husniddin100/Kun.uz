package com.example.service;

import com.example.dto.articleDTO.ArticleLikeDTO;
import com.example.entity.ArticleLikeEntity;
import com.example.exp.AppBadException;
import com.example.repository.ArticleLikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class ArticleLikeService {
    @Autowired
    private ArticleLikeRepository articleLikeRepository;

    public boolean createLike(ArticleLikeDTO dto) {
        Optional<Boolean> optional = articleLikeRepository.findByProfileIdAndArticleId(dto.getProfileId(), dto.getArticleId(), dto.getStatus());
        if (optional.isPresent()) {
            throw new AppBadException("already liked  this article");
        }
        Optional<Boolean> optional1 = articleLikeRepository.findByProfileIdAndArticleId1(dto.getProfileId(), dto.getArticleId());
        if (optional1.isPresent()) {
            remove(dto.getArticleId(), dto.getProfileId());
        }

        ArticleLikeEntity entity = new ArticleLikeEntity();
        entity.setProfileId(dto.getProfileId());
        entity.setArticleId(dto.getArticleId());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setStatus(dto.getStatus());
        articleLikeRepository.save(entity);
        return true;
    }

    public Boolean remove(String articleId, Integer profileId) {
        Optional<Boolean> optional = articleLikeRepository.findByProfileIdAndArticleId1(profileId, articleId);
        if (optional.isEmpty()) {
            throw new AppBadException("the article has not been liked");
        }
        articleLikeRepository.deleteLike(profileId, articleId);
        log.warn(" remove like or dislike");
        return true;
    }
}
