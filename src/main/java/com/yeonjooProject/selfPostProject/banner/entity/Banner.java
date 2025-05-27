package com.yeonjooProject.selfPostProject.banner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bannerId;

    @Column(nullable = false, length = 255)
    private String imageUrl; // 이미지 URL

    @Column(nullable = false)
    private Integer displayOrder; // 정렬 순서

    @Column(nullable = false, length = 255)
    private String title; // 제목

    @Column(nullable = false, length = 255)
    private String targetUrl; // 광고 URL

    // 값을 변경하는 메서드 추가
    public void update(String imageUrl, Integer displayOrder, String title, String targetUrl) {
        this.imageUrl = imageUrl;
        this.displayOrder = displayOrder;
        this.title = title;
        this.targetUrl = targetUrl;
    }
}