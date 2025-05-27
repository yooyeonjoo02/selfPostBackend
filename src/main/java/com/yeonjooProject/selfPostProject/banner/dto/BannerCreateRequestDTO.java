package com.yeonjooProject.selfPostProject.banner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerCreateRequestDTO {

    @NotBlank
    private String imageUrl;

    @NotNull
    private Integer displayOrder;

    @NotBlank
    private String title;

    @NotBlank
    private String targetUrl;
}
