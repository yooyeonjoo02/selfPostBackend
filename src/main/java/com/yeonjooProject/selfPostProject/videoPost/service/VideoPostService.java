package com.yeonjooProject.selfPostProject.videoPost.service;

import com.yeonjooProject.selfPostProject.category.entity.Category;
import com.yeonjooProject.selfPostProject.category.repository.CategoryRepository;
import com.yeonjooProject.selfPostProject.videoPost.dto.VideoPostRequestDTO;
import com.yeonjooProject.selfPostProject.videoPost.dto.VideoPostResponseDTO;
import com.yeonjooProject.selfPostProject.videoPost.entity.VideoPost;
import com.yeonjooProject.selfPostProject.videoPost.repository.VideoPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoPostService {

    private final VideoPostRepository videoPostRepository;
    private final CategoryRepository categoryRepository;

    // 게시물 생성
    public VideoPostResponseDTO createVideoPost(VideoPostRequestDTO requestDTO) {
        // 카테고리 확인 및 가져오기
        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + requestDTO.getCategoryId()));

        // VideoPost 엔티티 생성 (생성자를 사용하여 값 설정)
        VideoPost videoPost = new VideoPost(
                null, // ID는 자동 생성
                requestDTO.getTitle(),
                requestDTO.getContent(),
                requestDTO.getYoutubeUrl(),
                category,
                null // 생성 시간은 @PrePersist로 처리
        );

        // 저장 후 엔티티 반환
        VideoPost savedPost = videoPostRepository.save(videoPost);

        return mapToResponseDTO(savedPost);
    }

    // 모든 게시물 조회
    public List<VideoPostResponseDTO> getAllVideoPosts() {
        return videoPostRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // ID로 게시물 조회
    public VideoPostResponseDTO getVideoPostById(Long id) {
        VideoPost videoPost = videoPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("VideoPost not found with id: " + id));
        return mapToResponseDTO(videoPost);
    }

    // 게시물 수정
    public VideoPostResponseDTO updateVideoPost(Long id, VideoPostRequestDTO requestDTO) {
        // 기존 게시글 찾기
        VideoPost videoPost = videoPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("VideoPost not found with id: " + id));

        // 제목, 내용, YouTube URL 업데이트
        VideoPost updatedPost = new VideoPost(
                videoPost.getId(),
                requestDTO.getTitle(),
                requestDTO.getContent(),
                requestDTO.getYoutubeUrl(),
                videoPost.getCategory(), // 카테고리는 변경하지 않음
                videoPost.getCreatedAt()
        );

        VideoPost savedPost = videoPostRepository.save(updatedPost);
        return mapToResponseDTO(savedPost);
    }

    // 게시물 삭제
    public void deleteVideoPost(Long id) {
        if (!videoPostRepository.existsById(id)) {
            throw new IllegalArgumentException("VideoPost not found with id: " + id);
        }
        videoPostRepository.deleteById(id);
    }

    // 엔티티를 응답 DTO로 변환
    private VideoPostResponseDTO mapToResponseDTO(VideoPost videoPost) {
        return new VideoPostResponseDTO(
                videoPost.getId(),
                videoPost.getTitle(),
                videoPost.getContent(),
                videoPost.getYoutubeUrl(),
                videoPost.getCategory().getId(),
                videoPost.getCreatedAt()
        );
    }
}
