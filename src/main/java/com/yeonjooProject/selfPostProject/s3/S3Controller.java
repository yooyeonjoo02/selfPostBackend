package com.yeonjooProject.selfPostProject.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

// 사용자가 파일을 업로드하면, 이 컨트롤러는 파일을 AWS S3에 업로드하고 업로드된 파일의 URL을 반환

@RestController
@RequestMapping("/api/s3")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    /**
     * 파일 업로드 API
     *
     * @param file       업로드할 파일
     * @param folderPath 저장할 폴더 경로 (옵션, 기본값 "")
     * @return 업로드된 파일의 URL
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "folderPath", defaultValue = "") String folderPath) {

        String fileUrl = s3Service.uploadFile(file, folderPath);
        return ResponseEntity.ok(fileUrl);
    }
}