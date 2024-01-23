package com.example.controller;

import com.example.dto.ArticleTypeDTO;
import com.example.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/articleType")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;
    @PostMapping("/create")
    public ResponseEntity<ArticleTypeDTO> create(@RequestBody ArticleTypeDTO dto){
        return ResponseEntity.ok(articleTypeService.create(dto));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean>update(@PathVariable("id") Integer id,
                                                @RequestBody ArticleTypeDTO dto){
        return ResponseEntity.ok(articleTypeService.update(id,dto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean>delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(articleTypeService.delete(id));
    }
    @GetMapping("/all")
    public ResponseEntity<PageImpl> getAllByPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(articleTypeService.getAllByPagination(page, size));
    }
}
