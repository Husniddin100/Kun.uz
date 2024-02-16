package com.example.controller;

import com.example.dto.commentDTO.CommentLikeDTO;
import com.example.service.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commentLike")
public class CommentLikeController {
    @Autowired
    private CommentLikeService commentLikeService;
    @PostMapping("/create")
    public ResponseEntity<Boolean> createLike(@RequestBody CommentLikeDTO dto){
        return ResponseEntity.ok(commentLikeService.createLike(dto));
    }
    @DeleteMapping("/remove/{commentId}/{profileId}")
    public ResponseEntity<Boolean> remove(@PathVariable("commentId") Integer commentId, @PathVariable("profileId") Integer profileId) {
        return ResponseEntity.ok(commentLikeService.remove(commentId, profileId));

    }
}


