package com.yeonjooProject.selfPostProject.comment.controller;

import com.yeonjooProject.selfPostProject.comment.dto.CommentRequestDTO;
import com.yeonjooProject.selfPostProject.comment.dto.CommentResponseDTO;
import com.yeonjooProject.selfPostProject.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentRequestDTO requestDTO,
                                                            @RequestParam Long authorId) {
        return ResponseEntity.ok(commentService.createComment(requestDTO, authorId));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable Long commentId,
                                                            @RequestParam String newContent) {
        return ResponseEntity.ok(commentService.updateComment(commentId, newContent));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
