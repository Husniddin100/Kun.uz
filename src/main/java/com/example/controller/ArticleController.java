package com.example.controller;

import com.example.dto.articleDTO.ArticleShortInfoDTO;
import com.example.dto.articleDTO.CreateArticleDTO;
import com.example.enums.ArticleStatus;
import com.example.enums.LangEnum;
import com.example.service.ArticleService;
import com.example.util.SpringSecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Page;
import org.intellij.lang.annotations.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<CreateArticleDTO> createArticle(@RequestBody CreateArticleDTO dto
    ) {
        Integer moderatorId = SpringSecurityUtil.getCurrentUser().getId();
        log.info("Creat article ");
        return ResponseEntity.ok(articleService.create(dto, moderatorId));
    }

    @Operation(summary = "Api for Update Article", description = "this api used for Updating article")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<CreateArticleDTO> update(@PathVariable String id, @RequestBody CreateArticleDTO dto, @RequestHeader(value = "Accept-Language", defaultValue = "ru") LangEnum language) {
        log.info("update article {}", dto.getTitle());
        return ResponseEntity.ok(articleService.update(id, dto, language));

    }

    @Operation(summary = "Api for delete Article", description = "this api used for delete byId article")
    @PutMapping("/adm/delete/{id}")
    public ResponseEntity<Boolean> changeVisible(@PathVariable String id, @RequestHeader(value = "Accept-Language", defaultValue = "ru") LangEnum language) {
        return ResponseEntity.ok(articleService.delete(id, language));
    }

    @PreAuthorize("hasRole('PUBLISHER')")
    @Operation(summary = "Api for  Article status changed", description = "this api used for change status")
    @PutMapping("/adm/change_status/{id}/status")
    public ResponseEntity<Boolean> changeStatus(@PathVariable String id, @RequestParam(value = "status", defaultValue = "Published") ArticleStatus status
            , @RequestHeader(value = "Accept-Language", defaultValue = "ru") LangEnum language) {
        Integer publisherId = SpringSecurityUtil.getCurrentUser().getId();
        log.info("Article status changed {}", status.toString());
        return ResponseEntity.ok(articleService.changeStatusSave(id, publisherId, status, language));
    }

    @GetMapping("/typeList")
    public ResponseEntity<List<CreateArticleDTO>> getTypeArticleList(@RequestHeader(value = "Accept-Language", defaultValue = "ru") LangEnum language) {
        List<CreateArticleDTO> list = articleService.getTypeArticleList(language);
        return ResponseEntity.ok(list);
    }
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Article get last 5 by type id", description = "This method used to getting 5 articles last published by article type id")
    @GetMapping("/get_last5/{type_id}")
    public ResponseEntity<?> getLast5(@PathVariable("type_id") Integer id) {

        log.info("Article get last 5 by article type : typeId = {}", id);

        List<ArticleShortInfoDTO> result = articleService.getLast5(id);
        return ResponseEntity.ok(result);
    }


}
