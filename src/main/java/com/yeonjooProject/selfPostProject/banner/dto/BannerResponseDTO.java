package com.yeonjooProject.selfPostProject.banner.dto;

import com.yeonjooProject.selfPostProject.banner.entity.Banner;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerResponseDTO {

    private Long bannerId;
    private String imageUrl;
    private Integer displayOrder;
    private String title;
    private String targetUrl;

    public BannerResponseDTO(Banner banner) {
        this.bannerId = banner.getBannerId();
        this.imageUrl = banner.getImageUrl();
        this.displayOrder = banner.getDisplayOrder();
        this.title = banner.getTitle();
        this.targetUrl = banner.getTargetUrl();
    }
}


