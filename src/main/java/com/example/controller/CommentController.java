package com.example.controller;

import com.example.dto.commentDTO.CommentDTO;
import com.example.dto.commentDTO.CommentFilterDTO;
import com.example.dto.commentDTO.CommentListDTO;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
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

    @GetMapping("/getList")
    public ResponseEntity<PageImpl> getAllByPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(commentService.getAllByPagination(page, size));
    }

    @PostMapping("/filter")
    public ResponseEntity<PageImpl<CommentDTO>> create(@RequestBody CommentFilterDTO dto,
                                                       @RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<CommentDTO> result = commentService.filter(dto, page, size);
        return ResponseEntity.ok(result);
    }
}
