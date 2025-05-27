package com.yeonjooProject.selfPostProject.videoPost.repository;

import com.yeonjooProject.selfPostProject.videoPost.entity.VideoPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoPostRepository extends JpaRepository<VideoPost, Long> {
}
