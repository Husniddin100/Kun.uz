package com.example.controller;

import com.example.dto.CommentDTO;
import com.example.dto.CommentListDTO;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<Boolean> createComment(@RequestBody CommentDTO dto) {
        return ResponseEntity.ok(commentService.createComment(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateComment(@PathVariable Integer id, @RequestBody CommentDTO dto) {
        return ResponseEntity.ok(commentService.updateComment(id, dto));
    }

    @PutMapping("/visibleUpdate/{id}")
    public ResponseEntity<Boolean> visibleUpdate(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.visibleUpdate(id));
    }

    @GetMapping("/commentList/{id}")
    public ResponseEntity<List<CommentListDTO>> getCommentList(@PathVariable String id) {
        List<CommentListDTO> list = commentService.getList(id);
        return ResponseEntity.ok(list);
    }
}
