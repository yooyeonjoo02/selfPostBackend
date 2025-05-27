package com.yeonjooProject.selfPostProject.banner.controller;

import com.yeonjooProject.selfPostProject.banner.dto.BannerResponseDTO;
import com.yeonjooProject.selfPostProject.banner.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/banners")
@RequiredArgsConstructor
public class BannerController {

    private final BannerService bannerService;

    // 배너 생성 (폼 데이터 처리)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BannerResponseDTO> createBanner(
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("displayOrder") Integer displayOrder,
            @RequestParam("title") String title,
            @RequestParam("targetUrl") String targetUrl) {
        BannerResponseDTO responseDTO = bannerService.createBanner(imageFile, displayOrder, title, targetUrl);
        return ResponseEntity.ok(responseDTO);
    }

    // 배너 업데이트 (폼 데이터 처리)
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BannerResponseDTO> updateBanner(
            @PathVariable Long id,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam("displayOrder") Integer displayOrder,
            @RequestParam("title") String title,
            @RequestParam("targetUrl") String targetUrl) {
        BannerResponseDTO responseDTO = bannerService.updateBanner(id, imageFile, displayOrder, title, targetUrl);
        return ResponseEntity.ok(responseDTO);
    }

    // ✅ 특정 배너 조회
    @GetMapping("/{id}")
    public ResponseEntity<BannerResponseDTO> getBannerById(@PathVariable Long id) {
        BannerResponseDTO responseDTO = bannerService.getBannerById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<BannerResponseDTO>> getAllBanners() {
        List<BannerResponseDTO> banners = bannerService.getAllBanners();
        return ResponseEntity.ok(banners);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<BannerResponseDTO> updateBanner(
//            @PathVariable Long id,
//            @Validated @RequestBody BannerCreateRequestDTO requestDTO) {
//        BannerResponseDTO responseDTO = bannerService.updateBanner(id, requestDTO);
//        return ResponseEntity.ok(responseDTO);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBanner(@PathVariable Long id) {
        bannerService.deleteBanner(id);
        return ResponseEntity.noContent().build();
    }
}
