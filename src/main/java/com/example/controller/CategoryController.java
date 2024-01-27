package com.example.controller;

import com.example.dto.CategoryDTO;
import com.example.dto.JWTDTO;
import com.example.entity.CategoryEntity;
import com.example.enums.LangEnum;
import com.example.enums.ProfileRole;
import com.example.service.CategoryService;
import com.example.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO>craete(@RequestBody CategoryDTO dto,
                                             @RequestHeader(value = "Authorization")String jwt){
        JWTDTO jwtdto= JWTUtil.decode(jwt);
        if (!jwtdto.getRole().equals(ProfileRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(categoryService.create(dto));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateById(@PathVariable Integer id ,@RequestBody CategoryDTO dto,
                                              @RequestHeader(value = "Authorization")String jwt){
        JWTDTO jwtdto= JWTUtil.decode(jwt);
        if (!jwtdto.getRole().equals(ProfileRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(categoryService.update(id,dto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean>deleteById(@PathVariable Integer id,
                                             @RequestHeader(value = "Authorization")String jwt){
        JWTDTO jwtdto= JWTUtil.decode(jwt);
        if (!jwtdto.getRole().equals(ProfileRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(categoryService.delete(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>>getAll(){
        List<CategoryDTO> categoryList=categoryService.getAll();
        return ResponseEntity.ok(categoryList);
    }
    @GetMapping("/lang")
    public ResponseEntity<List<CategoryDTO>>lang(@RequestParam(value = "lang",defaultValue = "uz") LangEnum lang){
        List<CategoryDTO>langlist=categoryService.getLang(lang);
        return ResponseEntity.ok(langlist);

    }
}

