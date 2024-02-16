package com.example.controller;

import com.example.dto.articleDTO.ArticleTypeDTO;
import com.example.enums.LangEnum;
import com.example.service.ArticleTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "ArticleType Api list", description = "Api list for ArticleType")
@RequestMapping("/articleType")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;
    @Operation(summary = "api for creat articleType", description = "this api only created articleType")
    @PostMapping("/adm")
    public ResponseEntity<ArticleTypeDTO> create(@RequestBody ArticleTypeDTO dto) {
        log.info("create articleType {}", dto.getName_uz());
        return ResponseEntity.ok(articleTypeService.create(dto));
    }

    @PutMapping("/adm/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id,
                                          @RequestBody ArticleTypeDTO dto) {

        return ResponseEntity.ok(articleTypeService.update(id, dto));
    }

    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleTypeService.delete(id));
    }

    @GetMapping("/adm/all")
    public ResponseEntity<PageImpl> getAllByPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(articleTypeService.getAllByPagination(page, size));
    }

    @GetMapping("/lang")
    public ResponseEntity<List<ArticleTypeDTO>> getArticleTypesByLanguage(@RequestParam(value = "lang", defaultValue = "en")
                                                                          LangEnum language) {
        return ResponseEntity.ok(articleTypeService.getByLanguage(language));
    }
}

