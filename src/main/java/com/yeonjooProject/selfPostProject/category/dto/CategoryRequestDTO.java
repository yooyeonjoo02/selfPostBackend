package com.yeonjooProject.selfPostProject.category.dto;

import lombok.Data;

@Data
public class CategoryRequestDTO {
    private String name; // 카테고리 이름
//    private Integer displayOrder; // 정렬 순서
    private Long parentId; // 부모 카테고리 ID (선택적)
    private String type;
}
