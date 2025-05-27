package com.yeonjooProject.selfPostProject.post.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostRequestDTO {
    private String title;
    private String content;
    private Long categoryId;
    private List<MultipartFile> imageFiles; // 여러 개의 이미지 파일을 받을 수 있도록 변경
}
