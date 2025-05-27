package com.yeonjooProject.selfPostProject.category.dto;

import lombok.Data;

@Data
public class CategoryOrderUpdateRequestDTO {
    private String direction; // "up" 또는 "down"
}
