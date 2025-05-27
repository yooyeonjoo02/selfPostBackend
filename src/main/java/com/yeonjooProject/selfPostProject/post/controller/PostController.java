package com.yeonjooProject.selfPostProject.post.controller;

import com.yeonjooProject.selfPostProject.post.dto.PostRequestDTO;
import com.yeonjooProject.selfPostProject.post.dto.PostResponseDTO;
import com.yeonjooProject.selfPostProject.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//@RestController
//@RequestMapping("/api/posts")
//@RequiredArgsConstructor
//public class PostController {
//
//    private final PostService postService;
//
////    @PostMapping
////    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO requestDTO,
////                                                      @RequestParam Long authorId) {
////        return ResponseEntity.ok(postService.createPost(requestDTO, authorId));
////    }
//
//    // 게시글 생성 (JSON + 이미지)
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<PostResponseDTO> createPost(
//            @RequestPart("post") PostRequestDTO requestDTO,
//            @RequestPart(value = "image", required = false) MultipartFile image,
//            @RequestParam Long authorId) {
//        return ResponseEntity.ok(postService.createPost(requestDTO, image, authorId));
//    }
//
//    @GetMapping("/category/{categoryId}")
//    public ResponseEntity<List<PostResponseDTO>> getPostsByCategory(@PathVariable Long categoryId) {
//        return ResponseEntity.ok(postService.getPostsByCategory(categoryId));
//    }
//
//    @GetMapping("/{postId}")
//    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long postId) {
//        return ResponseEntity.ok(postService.getPostById(postId));
//    }
//
////    @PutMapping("/{postId}")
////    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long postId,
////                                                      @RequestBody PostRequestDTO requestDTO) {
////        return ResponseEntity.ok(postService.updatePost(postId, requestDTO));
////    }
//
//    // 게시글 수정 (JSON + 이미지)
//    @PutMapping(value = "/{postId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<PostResponseDTO> updatePost(
//            @PathVariable Long postId,
//            @RequestPart("post") PostRequestDTO requestDTO,
//            @RequestPart(value = "image", required = false) MultipartFile image) {
//        return ResponseEntity.ok(postService.updatePost(postId, requestDTO, image));
//    }
//
//    @DeleteMapping("/{postId}")
//    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
//        postService.deletePost(postId);
//        return ResponseEntity.ok("Post deleted successfully");
//    }
//}

//@RestController
//@RequestMapping("/api/posts")
//@RequiredArgsConstructor
//public class PostController {
//
//    private final PostService postService;
//
//    // 게시글 생성 (폼 데이터 처리)
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<PostResponseDTO> createPost(
//            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
//            @RequestParam("title") String title,
//            @RequestParam("content") String content,
//            @RequestParam("categoryId") Long categoryId,
//            @RequestParam("authorId") Long authorId) {
//
//        PostResponseDTO responseDTO = postService.createPost(imageFile, title, content, categoryId, authorId);
//        return ResponseEntity.ok(responseDTO);
//    }
//
//    // 게시글 수정 (폼 데이터 처리)
//    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<PostResponseDTO> updatePost(
//            @PathVariable Long id,
//            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
//            @RequestParam("title") String title,
//            @RequestParam("content") String content) {
//
//        PostResponseDTO responseDTO = postService.updatePost(id, imageFile, title, content);
//        return ResponseEntity.ok(responseDTO);
//    }
//
//    // 특정 게시글 조회
//    @GetMapping("/{id}")
//    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long id) {
//        PostResponseDTO responseDTO = postService.getPostById(id);
//        return ResponseEntity.ok(responseDTO);
//    }
//
//    // 특정 카테고리의 게시글 목록 조회
//    @GetMapping("/category/{categoryId}")
//    public ResponseEntity<List<PostResponseDTO>> getPostsByCategory(@PathVariable Long categoryId) {
//        List<PostResponseDTO> posts = postService.getPostsByCategoryId(categoryId);
//        return ResponseEntity.ok(posts);
//    }
//
//    // 모든 게시글 조회
//    @GetMapping
//    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
//        List<PostResponseDTO> posts = postService.getAllPosts();
//        return ResponseEntity.ok(posts);
//    }
//
//    // 게시글 삭제
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
//        postService.deletePost(id);
//        return ResponseEntity.noContent().build();
//    }
//}

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // ✅ 게시글 생성 (다중 이미지 업로드 지원)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostResponseDTO> createPost(
            @RequestParam(value = "imageFiles", required = false) List<MultipartFile> imageFiles,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("authorId") Long authorId) {

        PostResponseDTO responseDTO = postService.createPost(imageFiles, title, content, categoryId, authorId);
        return ResponseEntity.ok(responseDTO);
    }

//    // ✅ 게시글 수정 (기존 이미지 삭제 후 새로운 이미지 등록 가능)
//    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<PostResponseDTO> updatePost(
//            @PathVariable Long id,
//            @RequestParam(value = "imageFiles", required = false) List<MultipartFile> imageFiles,
//            @RequestParam("title") String title,
//            @RequestParam("content") String content) {
//
//        PostResponseDTO responseDTO = postService.updatePost(id, imageFiles, title, content);
//        return ResponseEntity.ok(responseDTO);
//    }

    // ✅ 게시글 수정 (기존 이미지 유지 + 삭제 + 새로운 이미지 추가)
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostResponseDTO> updatePost(
            @PathVariable Long id,
            @RequestParam(value = "imageFiles", required = false) List<MultipartFile> imageFiles,
            @RequestParam(value = "removedImageUrls", required = false) List<String> removedImageUrls, // 🔥 추가
            @RequestParam("title") String title,
            @RequestParam("content") String content) {

        PostResponseDTO responseDTO = postService.updatePost(id, imageFiles, removedImageUrls, title, content); // 🔥 수정된 서비스 메서드 호출
        return ResponseEntity.ok(responseDTO);
    }


    // ✅ 특정 게시글 조회 (이미지 포함)
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long id) {
        PostResponseDTO responseDTO = postService.getPostById(id);
        return ResponseEntity.ok(responseDTO);
    }

    // ✅ 특정 카테고리의 게시글 목록 조회
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByCategory(@PathVariable Long categoryId) {
        List<PostResponseDTO> posts = postService.getPostsByCategoryId(categoryId);
        return ResponseEntity.ok(posts);
    }

    // ✅ 모든 게시글 조회
    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
        List<PostResponseDTO> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // ✅ 게시글 삭제 (관련된 모든 이미지도 삭제)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
