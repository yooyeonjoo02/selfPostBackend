package com.yeonjooProject.selfPostProject.comment.dto;

import com.yeonjooProject.selfPostProject.comment.entity.Comment;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CommentResponseDTO {
    private Long id;
    private String content;
    private Long authorId;
    private String authorName;
    private Long postId;
    private Long parentId;
    private List<CommentResponseDTO> children; // 자식 댓글

    public CommentResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.authorId = comment.getAuthor().getId();
        this.authorName = comment.getAuthor().getUsername();
        this.postId = comment.getPost().getId();
        this.parentId = comment.getParent() != null ? comment.getParent().getId() : null;
        this.children = comment.getChildren().stream()
                .map(CommentResponseDTO::new)
                .collect(Collectors.toList());
    }
}
