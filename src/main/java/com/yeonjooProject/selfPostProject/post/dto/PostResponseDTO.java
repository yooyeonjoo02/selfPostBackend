package com.yeonjooProject.selfPostProject.post.dto;

import com.yeonjooProject.selfPostProject.post.entity.Post;
import com.yeonjooProject.selfPostProject.post.entity.PostImage;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private Long categoryId;
    private String authorName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> imageUrls; // 여러 개의 이미지 URL을 저장할 리스트

    public PostResponseDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.authorId = post.getAuthor().getId();
        this.categoryId = post.getCategory().getId();
        this.authorName = post.getAuthor().getUsername();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
//        this.imageUrls = post.getImages().stream()
//                .map(PostImage::getImageUrl)
//                .collect(Collectors.toList()); // 이미지 URL 리스트 변환

        // ✅ NullPointerException 방지: images가 null이면 빈 리스트 반환
        this.imageUrls = Optional.ofNullable(post.getImages())
                .orElse(Collections.emptyList()) // null이면 빈 리스트 반환
                .stream()
                .map(PostImage::getImageUrl)
                .collect(Collectors.toList());
    }
}
