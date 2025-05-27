package com.yeonjooProject.selfPostProject.post.service;

import com.yeonjooProject.selfPostProject.category.entity.Category;
import com.yeonjooProject.selfPostProject.category.repository.CategoryRepository;
import com.yeonjooProject.selfPostProject.member.entity.Member;
import com.yeonjooProject.selfPostProject.member.repository.MemberRepository;
import com.yeonjooProject.selfPostProject.post.dto.PostRequestDTO;
import com.yeonjooProject.selfPostProject.post.dto.PostResponseDTO;
import com.yeonjooProject.selfPostProject.post.entity.Post;
import com.yeonjooProject.selfPostProject.post.entity.PostImage;
import com.yeonjooProject.selfPostProject.post.repository.PostImageRepository;
import com.yeonjooProject.selfPostProject.post.repository.PostRepository;
import com.yeonjooProject.selfPostProject.s3.S3Service;
//import jakarta.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Service
//@RequiredArgsConstructor
//public class PostService {
//
//    private final PostRepository postRepository;
//    private final CategoryRepository categoryRepository;
//    private final MemberRepository memberRepository;
//
//    @Transactional
//    public PostResponseDTO createPost(PostRequestDTO requestDTO, MultipartFile image, Long authorId) {
//        // 작성자(Member) 조회
//        Member author = memberRepository.findById(authorId)
//                .orElseThrow(() -> new RuntimeException("Author not found"));
//
//        // 카테고리(Category) 조회
//        Category category = categoryRepository.findById(requestDTO.getCategoryId())
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//
//        // 게시글 생성
//        Post post = Post.builder()
//                .title(requestDTO.getTitle())
//                .content(requestDTO.getContent())
//                .imageUrl(requestDTO.getImageUrl())
//                .author(author)
//                .category(category)
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
//                .build();
//
//        postRepository.save(post);
//
//        return new PostResponseDTO(post);
//    }
//
//    @Transactional
//    public List<PostResponseDTO> getPostsByCategory(Long categoryId) {
//        List<Post> posts = postRepository.findAllByCategoryId(categoryId);
//        return posts.stream().map(PostResponseDTO::new).collect(Collectors.toList());
//    }
//
//    @Transactional
//    public PostResponseDTO getPostById(Long postId) {
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new RuntimeException("Post not found"));
//        return new PostResponseDTO(post);
//    }
//
//    @Transactional
//    public PostResponseDTO updatePost(Long postId, PostRequestDTO requestDTO, MultipartFile image) {
//        // 게시글 조회
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new RuntimeException("Post not found"));
//
//        // 엔티티의 업데이트 메서드 호출
//        post.update(requestDTO.getTitle(), requestDTO.getContent(), requestDTO.getImageUrl());
//
//        // 저장 후 DTO로 반환
//        return new PostResponseDTO(postRepository.save(post));
//    }
//
//    @Transactional
//    public void deletePost(Long postId) {
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new RuntimeException("Post not found"));
//
//        postRepository.delete(post);
//    }
//}

//@Service
//@RequiredArgsConstructor
//@Transactional
//public class PostService {
//
//    private final PostRepository postRepository;
//    private final MemberRepository memberRepository;
//    private final CategoryRepository categoryRepository;
//    private final PostImageRepository postImageRepository; // PostImage Repository 추가
//    private final S3Service s3Service; // S3 업로드 서비스
//
//    // 게시글 생성 (이미지 포함)
//    public PostResponseDTO createPost(MultipartFile imageFile, String title, String content, Long categoryId, Long authorId) {
//        // S3에 이미지 업로드
//        String imageUrl = null;
//        if (imageFile != null && !imageFile.isEmpty()) {
//            imageUrl = s3Service.uploadFile(imageFile, "posts/");
//        }
//
//        // 작성자 및 카테고리 조회
//        Member author = memberRepository.findById(authorId)
//                .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + authorId));
//
//        Category category = categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + categoryId));
//
//        // 게시글 저장
//        Post post = Post.builder()
//                .title(title)
//                .content(content)
//                .imageUrl(imageUrl)
//                .author(author)
//                .category(category)
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
//                .build();
//
//        Post savedPost = postRepository.save(post);
//        return new PostResponseDTO(savedPost);
//    }
//
//    // 게시글 수정
//    public PostResponseDTO updatePost(Long postId, MultipartFile imageFile, String title, String content) {
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));
//
//        // 기존 이미지 유지 또는 새 이미지 업로드
//        String imageUrl = post.getImageUrl();
//        if (imageFile != null && !imageFile.isEmpty()) {
//            imageUrl = s3Service.uploadFile(imageFile, "posts/");
//        }
//
//        // 업데이트 처리
//        post.update(title, content, imageUrl);
//        return new PostResponseDTO(post);
//    }
//
//    // 특정 게시글 조회
//    @Transactional(readOnly = true)
//    public PostResponseDTO getPostById(Long id) {
//        Post post = postRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));
//        return new PostResponseDTO(post);
//    }
//
//    // 특정 카테고리의 게시글 목록 조회
//    @Transactional(readOnly = true)
//    public List<PostResponseDTO> getPostsByCategoryId(Long categoryId) {
//        return postRepository.findAllByCategoryId(categoryId).stream()
//                .map(PostResponseDTO::new)
//                .collect(Collectors.toList());
//    }
//
//    // 모든 게시글 조회
//    @Transactional(readOnly = true)
//    public List<PostResponseDTO> getAllPosts() {
//        return postRepository.findAll().stream()
//                .map(PostResponseDTO::new)
//                .collect(Collectors.toList());
//    }
//
//    // 게시글 삭제
//    public void deletePost(Long id) {
//        if (!postRepository.existsById(id)) {
//            throw new IllegalArgumentException("Post not found with id: " + id);
//        }
//        postRepository.deleteById(id);
//    }
//}

//@Service
//@RequiredArgsConstructor
//@Transactional
//public class PostService {
//
//    private final PostRepository postRepository;
//    private final MemberRepository memberRepository;
//    private final CategoryRepository categoryRepository;
//    private final PostImageRepository postImageRepository; // PostImage Repository 추가
//    private final S3Service s3Service; // S3 업로드 서비스
//
//    // ✅ 게시글 생성 (여러 개의 이미지 포함)
//    public PostResponseDTO createPost(List<MultipartFile> imageFiles, String title, String content, Long categoryId, Long authorId) {
//        // 작성자 및 카테고리 조회
//        Member author = memberRepository.findById(authorId)
//                .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + authorId));
//
//        Category category = categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + categoryId));
//
//        // 게시글 저장
//        Post post = Post.builder()
//                .title(title)
//                .content(content)
//                .author(author)
//                .category(category)
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
//                .build();
//        postRepository.save(post);
//
//        // 이미지 업로드 및 저장
//        if (imageFiles != null && !imageFiles.isEmpty()) {
//            for (MultipartFile file : imageFiles) {
//                String imageUrl = s3Service.uploadFile(file, "posts/");
//                postImageRepository.save(new PostImage(imageUrl, post));
//            }
//        }
//
//        return new PostResponseDTO(post);
//    }
//
////    // ✅ 게시글 수정 (이미지 포함)
////    public PostResponseDTO updatePost(Long postId, List<MultipartFile> imageFiles, String title, String content) {
////        Post post = postRepository.findById(postId)
////                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));
////
////        // 기존 이미지 삭제
////        List<PostImage> existingImages = postImageRepository.findAllByPostId(postId);
////        for (PostImage image : existingImages) {
////            s3Service.deleteFile(image.getImageUrl()); // S3에서 삭제
////            postImageRepository.delete(image); // DB에서 삭제
////        }
////
////        // 게시글 정보 업데이트
////        post.update(title, content, LocalDateTime.now());
////
////        // 새로운 이미지 업로드
////        if (imageFiles != null && !imageFiles.isEmpty()) {
////            for (MultipartFile file : imageFiles) {
////                String imageUrl = s3Service.uploadFile(file, "posts/");
////                postImageRepository.save(new PostImage(imageUrl, post));
////            }
////        }
////
////        return new PostResponseDTO(post);
////    }
//
//    // ✅ 게시글 수정 (이미지 포함)
//    public PostResponseDTO updatePost(Long postId, List<MultipartFile> imageFiles, String title, String content) {
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));
//
//        // ✅ 기존 이미지 삭제
//        List<PostImage> existingImages = postImageRepository.findAllByPostId(postId);
//        for (PostImage image : existingImages) {
//            s3Service.deleteFile(image.getImageUrl()); // S3에서 삭제
//        }
//        postImageRepository.deleteAll(existingImages); // DB에서 삭제
//
//        // ✅ 새로운 이미지 업로드
//        List<String> newImageUrls = new ArrayList<>();
//        if (imageFiles != null && !imageFiles.isEmpty()) {
//            for (MultipartFile file : imageFiles) {
//                String imageUrl = s3Service.uploadFile(file, "posts/");
//                newImageUrls.add(imageUrl);
//            }
//        }
//
//        // ✅ 게시글 정보 업데이트 (새 이미지 리스트 포함)
//        post.update(title, content, newImageUrls);
//        return new PostResponseDTO(post);
//    }
//
//
//    // ✅ 특정 게시글 조회 (이미지 포함)
//    @Transactional(readOnly = true)
//    public PostResponseDTO getPostById(Long id) {
//        Post post = postRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));
//        return new PostResponseDTO(post);
//    }
//
//    // ✅ 특정 카테고리의 게시글 목록 조회
//    @Transactional(readOnly = true)
//    public List<PostResponseDTO> getPostsByCategoryId(Long categoryId) {
//        return postRepository.findAllByCategoryId(categoryId).stream()
//                .map(PostResponseDTO::new)
//                .collect(Collectors.toList());
//    }
//
//    // ✅ 모든 게시글 조회
//    @Transactional(readOnly = true)
//    public List<PostResponseDTO> getAllPosts() {
//        return postRepository.findAll().stream()
//                .map(PostResponseDTO::new)
//                .collect(Collectors.toList());
//    }
//
//    // ✅ 게시글 삭제 (관련 이미지도 삭제)
//    public void deletePost(Long id) {
//        Post post = postRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));
//
//        // S3에서 관련 이미지 삭제
//        List<PostImage> images = postImageRepository.findAllByPostId(id);
//        for (PostImage image : images) {
//            s3Service.deleteFile(image.getImageUrl()); // S3에서 삭제
//        }
//
//        // DB에서 이미지 삭제 후 게시글 삭제
//        postImageRepository.deleteAll(images);
//        postRepository.delete(post);
//    }
//}

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final PostImageRepository postImageRepository; // PostImage Repository 추가
    private final S3Service s3Service; // S3 업로드 서비스

    // ✅ 게시글 생성 (여러 개의 이미지 포함)
    public PostResponseDTO createPost(List<MultipartFile> imageFiles, String title, String content, Long categoryId, Long authorId) {
        // 작성자 및 카테고리 조회
        Member author = memberRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + authorId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + categoryId));

        // 게시글 저장
        Post post = Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .category(category)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        postRepository.save(post);

        // 이미지 업로드 및 저장
        if (imageFiles != null && !imageFiles.isEmpty()) {
            for (MultipartFile file : imageFiles) {
                String imageUrl = s3Service.uploadFile(file, "posts/");
                postImageRepository.save(new PostImage(imageUrl, post));
            }
        }

        return new PostResponseDTO(post);
    }

//    // ✅ 게시글 수정 (이미지 포함)
//    public PostResponseDTO updatePost(Long postId, List<MultipartFile> imageFiles, String title, String content) {
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));
//
//        // ✅ 기존 이미지 삭제
//        List<PostImage> existingImages = postImageRepository.findAllByPostId(postId);
//        for (PostImage image : existingImages) {
//            s3Service.deleteFile(image.getImageUrl()); // S3에서 삭제
//        }
//        postImageRepository.deleteAll(existingImages); // DB에서 삭제
//
//        // ✅ 새로운 이미지 업로드
//        List<String> newImageUrls = new ArrayList<>();
//        if (imageFiles != null && !imageFiles.isEmpty()) {
//            for (MultipartFile file : imageFiles) {
//                String imageUrl = s3Service.uploadFile(file, "posts/");
//                newImageUrls.add(imageUrl);
//            }
//        }
//
//        // ✅ 게시글 정보 업데이트 (새 이미지 리스트 포함)
//        post.update(title, content, newImageUrls);
//        return new PostResponseDTO(post);
//    }

    public PostResponseDTO updatePost(
            Long postId,
            List<MultipartFile> imageFiles,
            List<String> removedImageUrls,
            String title,
            String content
    ) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));

        // ✅ 1. 기존 이미지 목록 가져오기
        List<PostImage> existingImages = postImageRepository.findAllByPostId(postId);

        // ✅ 2. 삭제 대상 이미지 삭제
        List<PostImage> imagesToDelete = existingImages.stream()
                .filter(image -> removedImageUrls != null && removedImageUrls.contains(image.getImageUrl()))
                .collect(Collectors.toList());

        for (PostImage image : imagesToDelete) {
            s3Service.deleteFile(image.getImageUrl()); // S3에서 삭제
        }

        postImageRepository.deleteAll(imagesToDelete); // DB에서 삭제

        // ✅ 3. 삭제되지 않은 기존 이미지 URL은 유지
        List<String> remainingImageUrls = existingImages.stream()
                .filter(image -> removedImageUrls == null || !removedImageUrls.contains(image.getImageUrl()))
                .map(PostImage::getImageUrl)
                .collect(Collectors.toList());

        // ✅ 4. 새로 추가된 이미지 업로드
        if (imageFiles != null && !imageFiles.isEmpty()) {
            for (MultipartFile file : imageFiles) {
                String imageUrl = s3Service.uploadFile(file, "posts/");
                remainingImageUrls.add(imageUrl); // 새 이미지도 추가
                postImageRepository.save(new PostImage(imageUrl, post));
            }
        }

        // ✅ 5. 게시글 정보 업데이트
        post.update(title, content, remainingImageUrls);

        return new PostResponseDTO(post);
    }



    // ✅ 특정 게시글 조회 (이미지 포함)
    @Transactional(readOnly = true)
    public PostResponseDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));
        return new PostResponseDTO(post);
    }

    // ✅ 특정 카테고리의 게시글 목록 조회
    @Transactional(readOnly = true)
    public List<PostResponseDTO> getPostsByCategoryId(Long categoryId) {
        return postRepository.findAllByCategoryId(categoryId).stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());
    }

    // ✅ 모든 게시글 조회
    @Transactional(readOnly = true)
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());
    }

    // ✅ 게시글 삭제 (관련 이미지도 삭제)
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));

        // S3에서 관련 이미지 삭제
        List<PostImage> images = postImageRepository.findAllByPostId(id);
        for (PostImage image : images) {
            s3Service.deleteFile(image.getImageUrl()); // S3에서 삭제
        }

        // DB에서 이미지 삭제 후 게시글 삭제
        postImageRepository.deleteAll(images);
        postRepository.delete(post);
    }
}