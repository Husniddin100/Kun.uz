package com.example.controller;

import com.example.dto.CategoryDTO;
import com.example.entity.CategoryEntity;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO>craete(@RequestBody CategoryDTO dto){
        return ResponseEntity.ok(categoryService.create(dto));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateById(@PathVariable Integer id ,@RequestBody CategoryDTO dto){
        return ResponseEntity.ok(categoryService.update(id,dto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean>deleteById(@PathVariable Integer id){
        return ResponseEntity.ok(categoryService.delete(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>>getAll(){
        List<CategoryDTO> categoryList=categoryService.getAll();
        return ResponseEntity.ok(categoryList);
    }
}

