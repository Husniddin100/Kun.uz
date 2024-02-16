package com.example.controller;

import com.example.dto.RegionDTO;
import com.example.enums.LangEnum;
import com.example.service.RegionService;
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
@Tag(name = "Region Api list", description = "Api list for Region")
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Api for Create Region", description = "this api used for creating region")
    @PostMapping("/adm")
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO dto) {
        log.info("create region{}", dto.getName_uz());
        return ResponseEntity.ok(regionService.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id, @RequestBody RegionDTO dto) {
        return ResponseEntity.ok(regionService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
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
