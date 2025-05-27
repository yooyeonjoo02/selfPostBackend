package com.yeonjooProject.selfPostProject.banner.service;

import com.yeonjooProject.selfPostProject.banner.dto.BannerResponseDTO;
import com.yeonjooProject.selfPostProject.banner.entity.Banner;
import com.yeonjooProject.selfPostProject.banner.repository.BannerRepository;
import com.yeonjooProject.selfPostProject.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BannerService {

    private final BannerRepository bannerRepository;
    private final S3Service s3Service; // S3 업로드 서비스

    // 배너 생성
    public BannerResponseDTO createBanner(MultipartFile imageFile, Integer displayOrder, String title, String targetUrl) {
        String imageUrl = s3Service.uploadFile(imageFile, "banners/"); // 파일 업로드 후 URL 반환
        Banner banner = Banner.builder()
                .imageUrl(imageUrl)
                .displayOrder(displayOrder)
                .title(title)
                .targetUrl(targetUrl)
                .build();
        Banner savedBanner = bannerRepository.save(banner);
        return new BannerResponseDTO(savedBanner);
    }

    // 배너 업데이트
    public BannerResponseDTO updateBanner(Long id, MultipartFile imageFile, Integer displayOrder, String title, String targetUrl) {
        Banner banner = bannerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Banner not found with id: " + id));

        String imageUrl = banner.getImageUrl(); // 기존 이미지 URL 유지
        if (imageFile != null && !imageFile.isEmpty()) {
            imageUrl = s3Service.uploadFile(imageFile, "banners/");
        }

        banner.update(imageUrl, displayOrder, title, targetUrl);
        Banner updatedBanner = bannerRepository.save(banner);
        return new BannerResponseDTO(updatedBanner);
    }

    // ✅ 특정 배너 조회
    public BannerResponseDTO getBannerById(Long id) {
        Banner banner = bannerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Banner not found with id: " + id));
        return new BannerResponseDTO(banner);
    }

    public List<BannerResponseDTO> getAllBanners() {
        return bannerRepository.findAll().stream()
                .map(BannerResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void deleteBanner(Long id) {
        if (!bannerRepository.existsById(id)) {
            throw new IllegalArgumentException("Banner not found with id: " + id);
        }
        bannerRepository.deleteById(id);
    }
}

