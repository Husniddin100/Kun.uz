package com.example.controller;

import com.example.dto.ArticleDTO;
import com.example.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping("/adm")
    public ResponseEntity<ArticleDTO>createArticle(@RequestBody  ArticleDTO dto){
        return ResponseEntity.ok(articleService.create(dto));
    }
}
