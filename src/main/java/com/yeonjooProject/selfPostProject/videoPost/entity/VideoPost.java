package com.yeonjooProject.selfPostProject.videoPost.entity;

import com.yeonjooProject.selfPostProject.category.entity.Category;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 게시물 제목

    @Lob
    private String content; // 게시물 내용

    @Column(nullable = false)
    private String youtubeUrl; // 유튜브 URL

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // 카테고리 ID

    private LocalDateTime createdAt; // 생성 시간

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
