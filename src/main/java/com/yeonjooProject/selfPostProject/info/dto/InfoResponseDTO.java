package com.yeonjooProject.selfPostProject.info.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoResponseDTO {
    private Long id;
    private String title; // 제목
    private String content; // HTML 본문 (이미지 포함 가능)
    private Long categoryId; // 카테고리 ID
}
