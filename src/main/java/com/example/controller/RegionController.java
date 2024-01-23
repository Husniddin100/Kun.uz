package com.example.controller;

import com.example.dto.RegionDTO;
import com.example.entity.RegionEntity;
import com.example.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Region")
public class RegionController {
    @Autowired
    private RegionService regionService;
    @PostMapping("/create")
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO dto){
        return ResponseEntity.ok(regionService.create(dto));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean>update(@PathVariable("id")Integer id ,@RequestBody RegionDTO dto){
        return ResponseEntity.ok(regionService.update(id,dto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean>deleteById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(regionService.delete(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<RegionDTO>>getAll(){
        return ResponseEntity.ok(regionService.getAll());
    }

}
