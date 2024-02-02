package com.example.service;

import com.example.dto.ArticleDTO;
import com.example.entity.ArticleEntity;
import com.example.entity.RegionEntity;
import com.example.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private RegionService regionService;


    public ArticleDTO create(ArticleDTO dto) {
        ArticleEntity entity=new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        return null;
    }
}
