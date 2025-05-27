package com.yeonjooProject.selfPostProject.category.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReorderRequestDTO {
    private Long id1;        // 교환할 첫 번째 카테고리 ID
    private Long id2;        // 교환할 두 번째 카테고리 ID
    private boolean isParent; // 부모 카테고리 여부
}

