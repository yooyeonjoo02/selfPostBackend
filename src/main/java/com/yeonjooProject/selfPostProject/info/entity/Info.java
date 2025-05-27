package com.yeonjooProject.selfPostProject.info.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 제목

    @Lob
    @Column(nullable = false)
    private String content; // HTML 형식으로 저장될 본문 (이미지 포함 가능)

    @Column(nullable = false)
    private Long categoryId; // 카테고리 ID
}
