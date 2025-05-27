package com.yeonjooProject.selfPostProject.post.repository;

import com.yeonjooProject.selfPostProject.post.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
    List<PostImage> findAllByPostId(Long postId);
}