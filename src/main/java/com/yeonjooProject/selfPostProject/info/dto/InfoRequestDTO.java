package com.yeonjooProject.selfPostProject.info.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoRequestDTO {
    @NotBlank
    private String title; // 제목

    @NotBlank
    private String content; // HTML 본문 (이미지 포함 가능)

    @NotNull
    private Long categoryId; // 카테고리 ID
}
