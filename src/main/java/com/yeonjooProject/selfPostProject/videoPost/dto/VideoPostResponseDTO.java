package com.yeonjooProject.selfPostProject.videoPost.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoPostResponseDTO {
    private Long id;            // 게시물 ID
    private String title;       // 게시물 제목
    private String content;     // 게시물 내용
    private String youtubeUrl;  // 유튜브 URL
    private Long categoryId;    // 카테고리 ID
    private LocalDateTime createdAt; // 생성 시간
}
