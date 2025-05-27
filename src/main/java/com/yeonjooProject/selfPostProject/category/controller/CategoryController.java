package com.yeonjooProject.selfPostProject.category.controller;

import com.yeonjooProject.selfPostProject.category.dto.CategoryRequestDTO;
import com.yeonjooProject.selfPostProject.category.dto.CategoryResponseDTO;
import com.yeonjooProject.selfPostProject.category.dto.ReorderRequestDTO;
import com.yeonjooProject.selfPostProject.category.entity.Category;
import com.yeonjooProject.selfPostProject.category.service.CategoryService;
import com.yeonjooProject.selfPostProject.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO requestDTO) {
        return ResponseEntity.ok(categoryService.createCategory(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getTopLevelCategories() {
        return ResponseEntity.ok(categoryService.getTopLevelCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return ResponseEntity.ok(new CategoryResponseDTO(category, null)); // DTO 반환
    }

    @GetMapping("/{parentId}/children")
    public ResponseEntity<List<CategoryResponseDTO>> getChildCategories(@PathVariable Long parentId) {
        return ResponseEntity.ok(categoryService.getChildCategories(parentId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO requestDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

    @PutMapping("/reorder")
    public ResponseEntity<?> reorderCategories(@RequestBody ReorderRequestDTO request) {
        categoryService.reorderCategories(request);
        return ResponseEntity.ok("Category order updated successfully!");
    }
}