package com.example.controller;

import com.example.dto.ArticleTypeDTO;
import com.example.dto.CreateArticleDTO;
import com.example.enums.ArticleStatus;
import com.example.enums.LangEnum;
import com.example.enums.ProfileRole;
import com.example.service.ArticleService;
import com.example.util.HttpRequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/adm")
    public ResponseEntity<CreateArticleDTO> createArticle(@RequestBody CreateArticleDTO dto,
                                                          HttpServletRequest request) {
        HttpRequestUtil.getJWTDTO(request, ProfileRole.MODERATOR);
        Integer moderatorId = HttpRequestUtil.getProfileId(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.create(dto, moderatorId));
    }

    @PutMapping("/adm/update/{id}")
    public ResponseEntity<CreateArticleDTO> update(@PathVariable String id, @RequestBody CreateArticleDTO dto,
                                                   HttpServletRequest request) {
        HttpRequestUtil.getJWTDTO(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.update(id, dto));
    }

    @PutMapping("/adm/delete/{id}")
    public ResponseEntity<Boolean> changeVisible(@PathVariable String id,
                                                 HttpServletRequest request) {
        HttpRequestUtil.getJWTDTO(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.delete(id));
    }

    @PutMapping("/adm/change_status/{id}/status")
    public ResponseEntity<Boolean> changeStatus(@PathVariable String id,
                                                HttpServletRequest request, @RequestParam(value = "status", defaultValue = "Published") ArticleStatus status) {
        HttpRequestUtil.getJWTDTO(request, ProfileRole.PUBLISHER);
        Integer publisherId = HttpRequestUtil.getProfileId(request, ProfileRole.PUBLISHER);
        return ResponseEntity.ok(articleService.changeStatusSave(id, publisherId, status));
    }

    @GetMapping("/getByIdandLang/{id}")
    public ResponseEntity<CreateArticleDTO> getByLangAndId(@PathVariable("id") String id, @RequestParam(value = "lang", defaultValue = "uz") LangEnum lang) {
        Optional<CreateArticleDTO> optional = articleService.getByLangAndId(id, lang);
    }
}
