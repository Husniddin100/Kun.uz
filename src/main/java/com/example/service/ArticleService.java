package com.example.service;

import com.example.dto.ArticleDTO;
import com.example.dto.JWTDTO;
import com.example.entity.*;
import com.example.enums.ArticleStatus;
import com.example.exp.AppBadException;
import com.example.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.time.LocalDateTime;
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

    public ArticleDTO create(ArticleDTO dto) {
        RegionEntity regionId = regionService.get(dto.getRegionId());
        CategoryEntity categoryId = categoryService.get(dto.getCategoryId());

        ProfileEntity moderatorId = profileService.get(dto.getModeratorId());


        ArticleEntity entity = new ArticleEntity();
        entity.setId(String.valueOf(UUID.randomUUID()));
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setPhotoId(dto.getPhotoId());
        entity.setRegion(regionId);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setCategory(categoryId);
        entity.setStatus(ArticleStatus.NotPublished);

        articleRepository.save(entity);

        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public ArticleDTO update(String id, ArticleDTO dto) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("article not found");
        }
        ArticleEntity entity = optional.get();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        articleRepository.save(entity);
        return dto;
    }

    public Boolean delete(String id) {
        Optional<ArticleEntity> optional = articleRepository.getById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("article not found");
        }
        articleRepository.deleteById(id);
        return true;
    }

    public Boolean changeStatus(String id) {
        Optional<ArticleEntity> optional = articleRepository.getById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("article not found");
        }
        articleRepository.changeStatus(id);
        return true;
    }

   /* public ArticleDTO orderBy5(ArticleStatus[] status) {
        if (status==null ){
            throw new AppBadException("status is null");
        }
        return articleRepository.
    }*/
}
