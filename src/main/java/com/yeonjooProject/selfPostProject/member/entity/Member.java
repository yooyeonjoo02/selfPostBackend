package com.yeonjooProject.selfPostProject.member.entity;

import com.yeonjooProject.selfPostProject.comment.entity.Comment;
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
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true) // 이메일도 유일성 보장
    private String email;

//    @Column(nullable = false)
//    private String role; // USER, ADMIN 등 역할 구분

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>(); // 사용자가 작성한 게시글

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>(); // 사용자가 작성한 댓글

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // Enum 타입 매핑
    private Role role;

    public enum Role {
        USER,
        ADMIN
    }

    // 비밀번호 설정 전용 메서드
    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
