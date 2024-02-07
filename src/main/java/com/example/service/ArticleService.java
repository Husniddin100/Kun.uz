package com.example.service;

import com.example.dto.CategoryDTO;
import com.example.dto.CreateArticleDTO;
import com.example.entity.*;
import com.example.enums.ArticleStatus;
import com.example.enums.LangEnum;
import com.example.exp.AppBadException;
import com.example.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private RegionService regionService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AttachService attachService;
    @Autowired
    private ProfileService profileService;

    public CreateArticleDTO create(CreateArticleDTO dto, Integer moderatorId) {
        ArticleEntity entity = new ArticleEntity();
        entity.setId(String.valueOf(UUID.randomUUID()));
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setPhotoId(dto.getPhotoId());
        entity.setRegionId(dto.getRegionId());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setCategoryId(dto.getCategoryId());
        entity.setStatus(ArticleStatus.NotPublished);
        entity.setModeratorId(moderatorId);
        articleRepository.save(entity);
        return dto;
    }

    public CreateArticleDTO update(String id, CreateArticleDTO dto) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("article not found");
        }
        ArticleEntity entity = optional.get();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setPhotoId(dto.getPhotoId());
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        articleRepository.save(entity);
        return dto;
    }

    public Boolean delete(String id) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("article not found");
        }
        articleRepository.deleteById(id);
        return true;
    }

    public Boolean changeStatus(String id, ArticleStatus status) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("article not found");
        }
        articleRepository.changeStatus(id, status);
        return true;
    }

    public Boolean changeStatusSave(String id, Integer publisherId, ArticleStatus status) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        ArticleEntity entity = optional.get();
        entity.setPublishedDate(LocalDateTime.now());
        entity.setPublisherId(publisherId);
        changeStatus(id, status);
        articleRepository.save(entity);
        return true;
    }

    public Optional<CreateArticleDTO> getByLangAndId(String id, LangEnum lang) {
        return null;


    }

   /* public CreateArticleDTO orderBy5(ArticleStatus[] status) {
        if (status==null ){
            throw new AppBadException("status is null");
        }
        return articleRepository.
    }*/
}
