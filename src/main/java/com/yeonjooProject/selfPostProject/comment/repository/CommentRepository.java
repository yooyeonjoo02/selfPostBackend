package com.yeonjooProject.selfPostProject.comment.repository;

import com.yeonjooProject.selfPostProject.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostIdAndParentIsNull(Long postId); // 특정 게시글의 최상위 댓글 조회
    List<Comment> findAllByParentId(Long parentId); // 특정 부모 댓글의 자식 댓글 조회
}
