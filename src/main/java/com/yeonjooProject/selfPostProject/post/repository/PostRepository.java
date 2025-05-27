package com.yeonjooProject.selfPostProject.post.repository;

import com.yeonjooProject.selfPostProject.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByCategoryId(Long categoryId); // 특정 카테고리에 속한 게시글 조회
}
