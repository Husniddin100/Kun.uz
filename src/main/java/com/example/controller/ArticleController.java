package com.example.controller;

import com.example.dto.articleDTO.CreateArticleDTO;
import com.example.enums.ArticleStatus;
import com.example.enums.LangEnum;
import com.example.service.ArticleService;
import com.example.util.SpringSecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(articleService.update(id, dto,language));

    }

    @Operation(summary = "Api for delete Article", description = "this api used for delete byId article")
    @PutMapping("/adm/delete/{id}")
    public ResponseEntity<Boolean> changeVisible(@PathVariable String id,@RequestHeader(value = "Accept-Language", defaultValue = "ru") LangEnum language) {
        return ResponseEntity.ok(articleService.delete(id,language));
    }

    @PreAuthorize("hasRole('PUBLISHER')")
    @Operation(summary = "Api for  Article status changed", description = "this api used for change status")
    @PutMapping("/adm/change_status/{id}/status")
    public ResponseEntity<Boolean> changeStatus(@PathVariable String id, @RequestParam(value = "status", defaultValue = "Published") ArticleStatus status
            , @RequestHeader(value = "Accept-Language", defaultValue = "ru") LangEnum language) {
        Integer publisherId = SpringSecurityUtil.getCurrentUser().getId();
        log.info("Article status changed {}", status.toString());
        return ResponseEntity.ok(articleService.changeStatusSave(id, publisherId, status,language));
    }

    @GetMapping("/typeList")
    public ResponseEntity<List<CreateArticleDTO>> getTypeArticleList(@RequestHeader(value = "Accept-Language", defaultValue = "ru") LangEnum language) {
        List<CreateArticleDTO> list = articleService.getTypeArticleList(language);
        return ResponseEntity.ok(list);
    }


}
