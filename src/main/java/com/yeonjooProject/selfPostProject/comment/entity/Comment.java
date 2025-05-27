package com.yeonjooProject.selfPostProject.comment.entity;

import com.yeonjooProject.selfPostProject.category.entity.Category;
import com.yeonjooProject.selfPostProject.member.entity.Member;
import com.yeonjooProject.selfPostProject.post.entity.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content; // 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Member author; // 댓글 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // 댓글이 속한 게시글

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = true)
    private Comment parent; // 부모 댓글 (대댓글일 경우)

//    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
//    private List<Comment> children = new ArrayList<>(); // 자식 댓글

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Comment> children = new ArrayList<>(); // 자식 댓글을 빈 리스트로 초기화

    public void update(String newContent) {
        this.content = newContent;
    }
}
