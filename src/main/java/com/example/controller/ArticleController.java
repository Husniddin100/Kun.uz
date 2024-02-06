package com.example.controller;

import com.example.dto.ArticleDTO;
import com.example.enums.ArticleStatus;
import com.example.enums.ProfileRole;
import com.example.service.ArticleService;
import com.example.util.HttpRequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping("/adm")
    public ResponseEntity<ArticleDTO>createArticle(@RequestBody  ArticleDTO dto,
                                                   HttpServletRequest request){
        HttpRequestUtil.getJWTDTO(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.create(dto));
    }
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<ArticleDTO>update(@PathVariable String id,@RequestBody ArticleDTO dto,
                                             HttpServletRequest request ){
        HttpRequestUtil.getJWTDTO(request, ProfileRole.MODERATOR);
       return ResponseEntity.ok(articleService.update(id,dto));
    }
    @PutMapping("/adm/delete/{id}")
    public ResponseEntity<Boolean>delete(@PathVariable String id,
                                         HttpServletRequest request){
        HttpRequestUtil.getJWTDTO(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.delete(id));
    }
    @PutMapping("/adm/change_status/{id}")
    public ResponseEntity<Boolean>changeStatus(@PathVariable String id,
                                                  HttpServletRequest request){
        HttpRequestUtil.getJWTDTO(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.changeStatus(id));
    }
   /* @GetMapping("/orderBy5/{type}")
    public ResponseEntity<ArticleDTO>orderByCreatedDate5(@PathVariable ArticleStatus...status){
    return ResponseEntity.ok(articleService.orderBy5(status));
    }*/
}
