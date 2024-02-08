package com.example.controller;

import com.example.dto.ArticleTypeDTO;
import com.example.dto.JWTDTO;
import com.example.dto.RegionDTO;
import com.example.entity.RegionEntity;
import com.example.enums.LangEnum;
import com.example.enums.ProfileRole;
import com.example.service.RegionService;
import com.example.util.HttpRequestUtil;
import com.example.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@Tag(name = "Region Api list", description = "Api list for Region")
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @Operation(summary = "Api for Create Region", description = "this api used for creating region")
    @PostMapping("/adm")
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO dto,
                                            HttpServletRequest request) {
        Integer id = HttpRequestUtil.getProfileId(request, ProfileRole.ADMIN, ProfileRole.MODERATOR);
        log.info("create region{}", dto.getName_uz());
        return ResponseEntity.ok(regionService.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id, @RequestBody RegionDTO dto,
                                          HttpServletRequest request) {
        HttpRequestUtil.getJWTDTO(request, ProfileRole.ADMIN, ProfileRole.MODERATOR);
        return ResponseEntity.ok(regionService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id,
                                              HttpServletRequest request) {
        HttpRequestUtil.getJWTDTO(request, ProfileRole.ADMIN, ProfileRole.MODERATOR);
        return ResponseEntity.ok(regionService.delete(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegionDTO>> getAll() {
        return ResponseEntity.ok(regionService.getAll());
    }

    @GetMapping("/lang/{language}")
    public ResponseEntity<List<RegionDTO>> getArticleTypesByLanguage(@PathVariable LangEnum language) {
        List<RegionDTO> region = regionService.getByLang(language);
        return ResponseEntity.ok(region);
    }
}
