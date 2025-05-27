package com.yeonjooProject.selfPostProject.comment.dto;

import lombok.Data;

@Data
public class CommentRequestDTO {
    private String content; // 댓글 내용
    private Long postId; // 게시글 ID
    private Long parentId; // 부모 댓글 ID (대댓글인 경우 선택적)
}
