package com.example.controller;

import com.example.dto.CommentDTO;
import com.example.dto.CommentListDTO;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateComment(@PathVariable Integer id, @RequestBody CommentDTO dto) {
        return ResponseEntity.ok(commentService.updateComment(id, dto));
    }
    @DeleteMapping("/update")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.deleteComment(id));
    }
    @GetMapping("/commentList/{id}")
    public ResponseEntity<List<CommentListDTO>> getCommentList(@PathVariable String id){
        List<CommentListDTO> list=commentService.getList(id);
        return ResponseEntity.ok(list);
    }
}
