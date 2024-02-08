package com.example.controller;

import com.example.dto.CategoryDTO;
import com.example.dto.JWTDTO;
import com.example.entity.CategoryEntity;
import com.example.enums.LangEnum;
import com.example.enums.ProfileRole;
import com.example.service.CategoryService;
import com.example.util.HttpRequestUtil;
import com.example.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "Category Api list", description = "Api list for Category")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Operation(summary = "Api for Create Category", description = "this api used for creating category")
    @PostMapping("/adm")
    public ResponseEntity<CategoryDTO> craete(@RequestBody CategoryDTO dto,
                                              HttpServletRequest request) {
        HttpRequestUtil.getProfileId(request, ProfileRole.ADMIN, ProfileRole.MODERATOR);
        log.info("create category {}", dto.getId());
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @PutMapping("/adm/update/{id}")
    public ResponseEntity<Boolean> updateById(@PathVariable Integer id, @RequestBody CategoryDTO dto,
                                              HttpServletRequest request) {
        HttpRequestUtil.getJWTDTO(request, ProfileRole.ADMIN, ProfileRole.MODERATOR);
        return ResponseEntity.ok(categoryService.update(id, dto));
    }

    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id,
                                              HttpServletRequest request) {
        HttpRequestUtil.getJWTDTO(request, ProfileRole.ADMIN, ProfileRole.MODERATOR);
        return ResponseEntity.ok(categoryService.delete(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<CategoryDTO> categoryList = categoryService.getAll();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/lang")
    public ResponseEntity<List<CategoryDTO>> lang(@RequestParam(value = "lang", defaultValue = "uz") LangEnum lang) {
        List<CategoryDTO> langlist = categoryService.getLang(lang);
        return ResponseEntity.ok(langlist);

    }
}

