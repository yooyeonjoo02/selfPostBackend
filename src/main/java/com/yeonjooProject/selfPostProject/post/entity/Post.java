package com.yeonjooProject.selfPostProject.post.entity;

import com.yeonjooProject.selfPostProject.category.entity.Category;
import com.yeonjooProject.selfPostProject.comment.entity.Comment;
import com.yeonjooProject.selfPostProject.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

//    @Column(length = 255)
//    private String imageUrl; // 이미지 URL

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Member author; // 게시글 작성자

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 생성 날짜

    @Column(nullable = false)
    private LocalDateTime updatedAt; // 수정 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // 게시글이 속한 카테고리

    // 이미지
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>(); // 게시글에 달린 댓글

    // 필드 업데이트를 처리하는 메서드
//    public void update(String title, String content, String imageUrl) {
//        this.title = title;
//        this.content = content;
//        this.imageUrl = imageUrl;
//        this.updatedAt = LocalDateTime.now(); // 수정 날짜 갱신
//    }

//    public void update(String title, String content, LocalDateTime imageUrls) {
//        this.title = title;
//        this.content = content;
//        this.updatedAt = LocalDateTime.now();
//
//        this.images.clear();  // 기존 이미지 제거
//        imageUrls.forEach(url -> this.images.add(new PostImage(url, this))); // 새 이미지 추가
//    }

    // ✅ 필드 업데이트 메서드 (수정된 부분)
    public void update(String title, String content, List<String> imageUrls) {
        this.title = title;
        this.content = content;
        this.updatedAt = LocalDateTime.now(); // 수정 날짜 갱신

        // 기존 이미지 삭제 후 새 이미지 추가
        this.images.clear();
        imageUrls.forEach(url -> this.images.add(new PostImage(url, this)));
    }
}
