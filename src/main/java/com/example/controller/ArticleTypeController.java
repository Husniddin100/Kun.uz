package com.example.controller;

import com.example.dto.ArticleTypeDTO;
import com.example.dto.JWTDTO;
import com.example.enums.LangEnum;
import com.example.enums.ProfileRole;
import com.example.service.ArticleTypeService;
import com.example.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articleType")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;
    @PostMapping("/create")
    public ResponseEntity<ArticleTypeDTO> create(@RequestBody ArticleTypeDTO dto,
                                                 @RequestHeader(value = "profileRole")String jwt){
        JWTDTO jwtdto= JWTUtil.decode(jwt);
        if (!jwtdto.getRole().equals(ProfileRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(articleTypeService.create(dto));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean>update(@PathVariable("id") Integer id,
                                                @RequestBody ArticleTypeDTO dto,
                                         @RequestHeader(value = "profileRole")String jwt){
        JWTDTO jwtdto= JWTUtil.decode(jwt);
        if (!jwtdto.getRole().equals(ProfileRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(articleTypeService.update(id,dto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean>delete(@PathVariable("id") Integer id,
                                         @RequestHeader(value = "profileRole")String jwt){
        JWTDTO jwtdto= JWTUtil.decode(jwt);
        if (!jwtdto.getRole().equals(ProfileRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(articleTypeService.delete(id));
    }
    @GetMapping("/all")
    public ResponseEntity<PageImpl> getAllByPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                       @RequestHeader(value = "profileRole")String jwt) {
        JWTDTO jwtdto= JWTUtil.decode(jwt);
        if (!jwtdto.getRole().equals(ProfileRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(articleTypeService.getAllByPagination(page, size));
    }
    @GetMapping("/lang")
    public ResponseEntity<List<ArticleTypeDTO>> getArticleTypesByLanguage(@RequestParam(value = "lang",defaultValue = "en")
                                                                                   LangEnum language) {
        return ResponseEntity.ok(articleTypeService.getByLanguage(language));
    }
}

