package com.example.controller;

import com.example.dto.CreateArticleDTO;
import com.example.enums.ArticleStatus;
import com.example.enums.ProfileRole;
import com.example.service.ArticleService;
import com.example.util.HttpRequestUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "Article Api list", description = "Api list for Article")
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Operation(summary = "Api for Create Article", description = "this api used for creating article")
    @PostMapping("/adm")
    public ResponseEntity<CreateArticleDTO> createArticle(@RequestBody CreateArticleDTO dto,
                                                          HttpServletRequest request) {
        HttpRequestUtil.getJWTDTO(request, ProfileRole.MODERATOR);
        Integer moderatorId = HttpRequestUtil.getProfileId(request, ProfileRole.MODERATOR);
        log.info("Creat article ");
        return ResponseEntity.ok(articleService.create(dto, moderatorId));
    }

    @Operation(summary = "Api for Update Article", description = "this api used for Updating article")
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<CreateArticleDTO> update(@PathVariable String id, @RequestBody CreateArticleDTO dto,
                                                   HttpServletRequest request) {
        HttpRequestUtil.getJWTDTO(request, ProfileRole.MODERATOR);
        log.info("update article {}", dto.getTitle());
        return ResponseEntity.ok(articleService.update(id, dto));

    }

    @Operation(summary = "Api for delete Article", description = "this api used for delete byId article")
    @PutMapping("/adm/delete/{id}")
    public ResponseEntity<Boolean> changeVisible(@PathVariable String id,
                                                 HttpServletRequest request) {
        HttpRequestUtil.getJWTDTO(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.delete(id));
    }

    @Operation(summary = "Api for  Article status changed", description = "this api used for change status")
    @PutMapping("/adm/change_status/{id}/status")
    public ResponseEntity<Boolean> changeStatus(@PathVariable String id,
                                                HttpServletRequest request, @RequestParam(value = "status", defaultValue = "Published") ArticleStatus status) {
        HttpRequestUtil.getJWTDTO(request, ProfileRole.PUBLISHER);
        Integer publisherId = HttpRequestUtil.getProfileId(request, ProfileRole.PUBLISHER);
        log.info("Article status changed {}", status.toString());
        return ResponseEntity.ok(articleService.changeStatusSave(id, publisherId, status));
    }

    @GetMapping("/typeList")
    public ResponseEntity<List<CreateArticleDTO>> getTypeArticleList() {
        List<CreateArticleDTO> list = articleService.getTypeArticleList();
        return ResponseEntity.ok(list);
    }



}
