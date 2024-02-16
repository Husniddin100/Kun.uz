package com.example.controller;

import com.example.dto.articleDTO.ArticleLikeDTO;
import com.example.service.ArticleLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/article_like")
public class ArticleLikeController {
    @Autowired
    private ArticleLikeService articleLikeService;

    @PostMapping("/createLike")
    public ResponseEntity<Boolean> createLike(@RequestBody ArticleLikeDTO dto) {
        log.info("Article like {}", dto.getArticleId());
        return ResponseEntity.ok(articleLikeService.createLike(dto));
    }

    @DeleteMapping("/remove/{articleId}/{profileId}")
    public ResponseEntity<Boolean> remove(@PathVariable("articleId") String articleId, @PathVariable("profileId") Integer profileId) {
        return ResponseEntity.ok(articleLikeService.remove(articleId, profileId));

    }
}
