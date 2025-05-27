package com.yeonjooProject.selfPostProject.videoPost.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoPostRequestDTO {
    private String title;       // 게시물 제목
    private String content;     // 게시물 내용
    private String youtubeUrl;  // 유튜브 URL
    private Long categoryId;    // 카테고리 ID
}
