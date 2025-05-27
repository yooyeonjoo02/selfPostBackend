package com.yeonjooProject.selfPostProject.comment.service;

import com.yeonjooProject.selfPostProject.comment.dto.CommentRequestDTO;
import com.yeonjooProject.selfPostProject.comment.dto.CommentResponseDTO;
import com.yeonjooProject.selfPostProject.comment.entity.Comment;
import com.yeonjooProject.selfPostProject.comment.repository.CommentRepository;
import com.yeonjooProject.selfPostProject.member.entity.Member;
import com.yeonjooProject.selfPostProject.member.repository.MemberRepository;
import com.yeonjooProject.selfPostProject.post.entity.Post;
import com.yeonjooProject.selfPostProject.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CommentResponseDTO createComment(CommentRequestDTO requestDTO, Long authorId) {
        // 작성자(Member) 조회
        Member author = memberRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        // 게시글(Post) 조회
        Post post = postRepository.findById(requestDTO.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // 부모 댓글(Comment) 조회 (대댓글인 경우)
        Comment parent = null;
        if (requestDTO.getParentId() != null) {
            parent = commentRepository.findById(requestDTO.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent comment not found"));
        }

        // 댓글 생성
        Comment comment = Comment.builder()
                .content(requestDTO.getContent())
                .author(author)
                .post(post)
                .parent(parent)
                .build();

        commentRepository.save(comment);

        return new CommentResponseDTO(comment);
    }


//    @Transactional
//    public CommentResponseDTO createComment(CommentRequestDTO requestDTO, Long authorId) {
//        // 작성자(Member) 조회
//        Member author = memberRepository.findById(authorId)
//                .orElseThrow(() -> new RuntimeException("Author not found"));
//
//        // 게시글(Post) 조회
//        Post post = postRepository.findById(requestDTO.getPostId())
//                .orElseThrow(() -> new RuntimeException("Post not found"));
//
//        // 부모 댓글(Comment) 조회 (대댓글인 경우)
//        Comment parent = null;
//        if (requestDTO.getParentId() != null) {
//            parent = commentRepository.findById(requestDTO.getParentId())
//                    .orElseThrow(() -> new RuntimeException("Parent comment not found"));
//        }
//
//        // 댓글 생성
//        Comment comment = Comment.builder()
//                .content(requestDTO.getContent())
//                .author(author)
//                .post(post)
//                .parent(parent)
//                .build();
//
//        commentRepository.save(comment);
//
//        return new CommentResponseDTO(comment);
//    }

    @Transactional(readOnly = true)
    public List<CommentResponseDTO> getCommentsByPost(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostIdAndParentIsNull(postId);
        return comments.stream().map(CommentResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public CommentResponseDTO updateComment(Long commentId, String newContent) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // 엔티티 내 업데이트 메서드 사용
        comment.update(newContent);

        return new CommentResponseDTO(commentRepository.save(comment));
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        commentRepository.delete(comment);
    }
}

