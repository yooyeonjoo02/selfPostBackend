package com.yeonjooProject.selfPostProject.videoPost.controller;

import com.yeonjooProject.selfPostProject.videoPost.dto.VideoPostRequestDTO;
import com.yeonjooProject.selfPostProject.videoPost.dto.VideoPostResponseDTO;
import com.yeonjooProject.selfPostProject.videoPost.service.VideoPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/api/videoposts")
//@RequiredArgsConstructor
//public class VideoPostController {
//
//    private final VideoPostService videoPostService;
//
//    // 게시물 생성
//    @PostMapping
//    public ResponseEntity<VideoPostResponseDTO> createVideoPost(@RequestBody VideoPostRequestDTO requestDTO) {
//        VideoPostResponseDTO createdPost = videoPostService.createVideoPost(requestDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
//    }
//
//    // 모든 게시물 조회
//    @GetMapping
//    public ResponseEntity<List<VideoPostResponseDTO>> getAllVideoPosts() {
//        List<VideoPostResponseDTO> posts = videoPostService.getAllVideoPosts();
//        return ResponseEntity.ok(posts);
//    }
//
//    // ID로 게시물 조회
//    @GetMapping("/{id}")
//    public ResponseEntity<VideoPostResponseDTO> getVideoPostById(@PathVariable Long id) {
//        VideoPostResponseDTO post = videoPostService.getVideoPostById(id);
//        return ResponseEntity.ok(post);
//    }
//
//    // 게시물 삭제
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteVideoPost(@PathVariable Long id) {
//        videoPostService.deleteVideoPost(id);
//        return ResponseEntity.noContent().build();
//    }
//}

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/videoposts")
@RequiredArgsConstructor
public class VideoPostController {

    private final VideoPostService videoPostService;

    // 게시물 생성
    @PostMapping
    public ResponseEntity<VideoPostResponseDTO> createVideoPost(@RequestBody VideoPostRequestDTO requestDTO) {
        VideoPostResponseDTO createdPost = videoPostService.createVideoPost(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    // 모든 게시물 조회
    @GetMapping
    public ResponseEntity<List<VideoPostResponseDTO>> getAllVideoPosts() {
        List<VideoPostResponseDTO> posts = videoPostService.getAllVideoPosts();
        return ResponseEntity.ok(posts);
    }

    // ID로 게시물 조회
    @GetMapping("/{id}")
    public ResponseEntity<VideoPostResponseDTO> getVideoPostById(@PathVariable Long id) {
        VideoPostResponseDTO post = videoPostService.getVideoPostById(id);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoPostResponseDTO> updateVideoPost(
            @PathVariable Long id,
            @RequestBody VideoPostRequestDTO requestDTO) {
        return ResponseEntity.ok(videoPostService.updateVideoPost(id, requestDTO));
    }

    // 게시물 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoPost(@PathVariable Long id) {
        videoPostService.deleteVideoPost(id);
        return ResponseEntity.noContent().build();
    }
}
