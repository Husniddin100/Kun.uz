package com.example.service;

import com.example.dto.articleDTO.ArticleShortInfoDTO;
import com.example.dto.articleDTO.CreateArticleDTO;
import com.example.entity.*;
import com.example.enums.ArticleStatus;
import com.example.enums.LangEnum;
import com.example.exp.AppBadException;
import com.example.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.intellij.lang.annotations.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleTypeService articleTypeService;

    public CreateArticleDTO create(CreateArticleDTO dto, Integer moderatorId) {
        ArticleEntity entity = new ArticleEntity();
        entity.setId(String.valueOf(UUID.randomUUID()));
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setPhotoId(dto.getPhotoId());
        entity.setRegionId(dto.getRegionId());
        entity.setModeratorId(moderatorId);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setCategoryId(dto.getCategoryId());
        entity.setStatus(ArticleStatus.NotPublished);
        articleRepository.save(entity);
        return dto;
    }

    public CreateArticleDTO update(String id, CreateArticleDTO dto, LangEnum language) {
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

    public Boolean delete(String id, LangEnum language) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("article not found");
        }
        log.warn("delete article");
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

    public Boolean changeStatusSave(String id, Integer publisherId, ArticleStatus status, LangEnum language) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        ArticleEntity entity = optional.get();
        entity.setPublishedDate(LocalDateTime.now());
        entity.setPublisherId(publisherId);
        changeStatus(id, status);
        articleRepository.save(entity);
        return true;
    }


    public List<CreateArticleDTO> getTypeArticleList(LangEnum language) {
        List<ArticleEntity> list = articleRepository.getTypeArticleList();
        List<CreateArticleDTO> entityList = new LinkedList<>();
        for (ArticleEntity entity : list) {
            entityList.add(toDTO(entity));
        }
        return entityList;
    }
    public List<ArticleShortInfoDTO> getLast5(Integer id) {
        TypeEntity articleType = articleTypeService.getById(id);
        List<ArticleShortInfoDTO> iMapperList = articleRepository.findTop5(articleType, ArticleStatus.Published);

        List<ArticleShortInfoDTO> dtoList = new ArrayList<>();

        for (ArticleShortInfoDTO iMapper : iMapperList) {
            dtoList.add(getShortDTO(iMapper));
        }
        return dtoList;
    }

    private ArticleShortInfoDTO getShortDTO(ArticleShortInfoDTO mapper) {
        ArticleShortInfoDTO dto = new ArticleShortInfoDTO();
        dto.setTitle(mapper.getTitle());
        dto.setDescription(mapper.getDescription());
        return dto;
    }

    public CreateArticleDTO toDTO(ArticleEntity entity) {
        CreateArticleDTO dto = new CreateArticleDTO();
        dto.setRegionId(entity.getRegionId());
        dto.setDescription(entity.getDescription());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setPhotoId(entity.getPhotoId());
        dto.setPhotoId(entity.getPhotoId());
        return dto;
    }
}
