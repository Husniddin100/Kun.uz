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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping("/adm/create")
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO dto,
                                            HttpServletRequest request) {
        Integer id = HttpRequestUtil.getProfileId(request, ProfileRole.ADMIN,ProfileRole.MODERATOR);
        return ResponseEntity.ok(regionService.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id, @RequestBody RegionDTO dto,
                                          @RequestHeader(value = "Authorization") String jwt) {
        JWTDTO jwtdto = JWTUtil.decode(jwt);
        if (!jwtdto.getRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(regionService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id,
                                              @RequestHeader(value = "Authorization") String jwt) {
        JWTDTO jwtdto = JWTUtil.decode(jwt);
        if (!jwtdto.getRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
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
