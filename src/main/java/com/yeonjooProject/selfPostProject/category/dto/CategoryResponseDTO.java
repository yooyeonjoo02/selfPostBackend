package com.yeonjooProject.selfPostProject.category.dto;

import com.yeonjooProject.selfPostProject.category.entity.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private Integer displayOrder;
    private String type; // 카테고리 유형
    private Long parentId; // 부모 카테고리 ID
    private List<CategoryResponseDTO> children; // 자식 카테고리

    public CategoryResponseDTO(Category category, List<CategoryResponseDTO> children) {
        this.id = category.getId();
        this.name = category.getName();
        this.displayOrder = category.getDisplayOrder();
        this.type = category.getType();
        this.parentId = category.getParent() != null ? category.getParent().getId() : null;
        this.children = children;
    }
}
