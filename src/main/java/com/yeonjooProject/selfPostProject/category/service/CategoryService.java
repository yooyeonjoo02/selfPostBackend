package com.yeonjooProject.selfPostProject.category.service;

import com.yeonjooProject.selfPostProject.category.dto.CategoryRequestDTO;
import com.yeonjooProject.selfPostProject.category.dto.CategoryResponseDTO;
import com.yeonjooProject.selfPostProject.category.dto.ReorderRequestDTO;
import com.yeonjooProject.selfPostProject.category.entity.Category;
import com.yeonjooProject.selfPostProject.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

//    @Transactional
//    public CategoryResponseDTO createCategory(CategoryRequestDTO requestDTO) {
//        // 부모 카테고리 확인
//        Category parent = null;
//        if (requestDTO.getParentId() != null) {
//            parent = categoryRepository.findById(requestDTO.getParentId())
//                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
//        }
//
//        // 부모 카테고리인 경우 type을 null로 설정
//        String type = (parent == null) ? null : requestDTO.getType();
//
//        // 자식 카테고리인데 type이 없으면 예외 처리
//        if (parent != null && (type == null || type.isBlank())) {
//            throw new IllegalArgumentException("Type is required for child categories");
//        }
//
//        // 카테고리 생성
//        Category category = Category.builder()
//                .name(requestDTO.getName())
//                .displayOrder(requestDTO.getDisplayOrder())
//                .type(type)
//                .parent(parent)
//                .build();
//
//        categoryRepository.save(category);
//
//        return new CategoryResponseDTO(category, List.of());
//    }

//    @Transactional
//    public CategoryResponseDTO createCategory(CategoryRequestDTO requestDTO) {
//        // 부모 카테고리 확인
//        Category parent = null;
//        if (requestDTO.getParentId() != null) {
//            parent = categoryRepository.findById(requestDTO.getParentId())
//                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
//        }
//
//        // 부모 카테고리인 경우 type을 null로 설정
//        String type = (parent == null) ? null : requestDTO.getType();
//
//        // 자식 카테고리인데 type이 없으면 예외 처리
//        if (parent != null && (type == null || type.isBlank())) {
//            throw new IllegalArgumentException("Type is required for child categories");
//        }
//
//        // 정렬 순서 자동 부여: 같은 레벨의 카테고리 중 최대 displayOrder 값을 찾아서 +1
//        Integer maxDisplayOrder = (parent == null)
//                ? categoryRepository.findMaxDisplayOrderByParentId(null) // 부모가 없는 경우 (최상위 카테고리)
//                : categoryRepository.findMaxDisplayOrderByParentId(parent.getId()); // 특정 부모의 자식 카테고리
//
//        int newDisplayOrder = (maxDisplayOrder != null) ? maxDisplayOrder + 1 : 1;
//
//        // 카테고리 생성
//        Category category = Category.builder()
//                .name(requestDTO.getName())
//                .displayOrder(newDisplayOrder) // 자동으로 계산된 정렬 순서 사용
//                .type(type)
//                .parent(parent)
//                .build();
//
//        categoryRepository.save(category);
//
//        return new CategoryResponseDTO(category, List.of());
//    }

    @Transactional
    public CategoryResponseDTO createCategory(CategoryRequestDTO requestDTO) {
        // 부모 카테고리 확인
        Category parent = null;
        if (requestDTO.getParentId() != null) {
            parent = categoryRepository.findById(requestDTO.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent category not found"));
        }

        // 부모 카테고리인 경우 type은 null로 설정
        String type = (parent == null) ? null : requestDTO.getType();

        // 자식 카테고리인데 type이 없으면 예외 처리
        if (parent != null && (type == null || type.isBlank())) {
            throw new IllegalArgumentException("Type is required for child categories");
        }

        // 정렬 순서 자동 부여
        Integer maxDisplayOrder = (parent == null)
                ? categoryRepository.findMaxDisplayOrderForParent()
                : categoryRepository.findMaxDisplayOrderForChildren(parent.getId());
        int newDisplayOrder = (maxDisplayOrder != null) ? maxDisplayOrder + 1 : 1;

        // 카테고리 생성
        Category category = Category.builder()
                .name(requestDTO.getName())
                .displayOrder(newDisplayOrder)
                .type(type)
                .parent(parent)
                .build();

        categoryRepository.save(category);

        return new CategoryResponseDTO(category, List.of());
    }




    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> getTopLevelCategories() {
        List<Category> categories = categoryRepository.findAllByParentIsNullOrderByDisplayOrder();
        return categories.stream()
                .map(category -> new CategoryResponseDTO(category, getChildCategories(category.getId())))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> getChildCategories(Long parentId) {
        List<Category> children = categoryRepository.findAllByParentIdOrderByDisplayOrder(parentId);
        return children.stream()
                .map(child -> new CategoryResponseDTO(child, getChildCategories(child.getId())))
                .collect(Collectors.toList());
    }

//    @Transactional
//    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO requestDTO) {
//        Category category = categoryRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//
//        // 부모 카테고리인 경우 type을 null로 강제 설정
//        String type = (category.getParent() == null) ? null : requestDTO.getType();
//
//        // 자식 카테고리인데 type이 없으면 예외 처리
//        if (category.getParent() != null && (type == null || type.isBlank())) {
//            throw new IllegalArgumentException("Type is required for child categories");
//        }
//
//        // update 메서드를 사용하여 필드 변경
//        category.update(requestDTO.getName(), requestDTO.getDisplayOrder(), type);
//
//        // 변경된 엔티티를 저장하고 DTO로 반환
//        Category updatedCategory = categoryRepository.save(category);
//        return new CategoryResponseDTO(updatedCategory, getChildCategories(updatedCategory.getId()));
//    }

    @Transactional
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO requestDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // 부모 카테고리인 경우 type을 null로 설정
        String type = (category.getParent() == null) ? null : requestDTO.getType();

        // 자식 카테고리인데 type이 없으면 예외 처리
        if (category.getParent() != null && (type == null || type.isBlank())) {
            throw new IllegalArgumentException("Type is required for child categories");
        }

        // update 메서드를 사용하여 필드 변경 (displayOrder는 유지)
        category.update(requestDTO.getName(), category.getDisplayOrder(), type);

        // 변경된 엔티티를 저장하고 DTO로 반환
        Category updatedCategory = categoryRepository.save(category);
        return new CategoryResponseDTO(updatedCategory, getChildCategories(updatedCategory.getId()));
    }



    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }

    @Transactional
    public void reorderCategories(ReorderRequestDTO request) {
        // 첫 번째 카테고리 가져오기
        Category category1 = categoryRepository.findById(request.getId1())
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + request.getId1()));

        // 두 번째 카테고리 가져오기
        Category category2 = categoryRepository.findById(request.getId2())
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + request.getId2()));

        // 순서 교환
        Integer tempOrder = category1.getDisplayOrder();
        category1.setDisplayOrder(category2.getDisplayOrder());
        category2.setDisplayOrder(tempOrder);

        // 변경 사항 저장
        categoryRepository.save(category1);
        categoryRepository.save(category2);
    }

}


