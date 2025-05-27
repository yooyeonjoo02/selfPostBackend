package com.yeonjooProject.selfPostProject.category.repository;

import com.yeonjooProject.selfPostProject.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByParentIsNullOrderByDisplayOrder(); // 최상위 카테고리 조회

    List<Category> findAllByParentIdOrderByDisplayOrder(Long parentId); // 특정 부모 카테고리의 자식 조회

    @Query("SELECT MAX(c.displayOrder) FROM Category c WHERE c.parent.id = :parentId")
    Integer findMaxDisplayOrderByParentId(@Param("parentId") Long parentId);

    // 특정 부모 ID를 가진 카테고리를 displayOrder 순으로 정렬하여 반환
    List<Category> findByParentIdOrderByDisplayOrder(Long parentId);

    @Query("SELECT COALESCE(MAX(c.displayOrder), 0) FROM Category c WHERE c.parent IS NULL")
    Integer findMaxDisplayOrderForParent();

    @Query("SELECT COALESCE(MAX(c.displayOrder), 0) FROM Category c WHERE c.parent.id = :parentId")
    Integer findMaxDisplayOrderForChildren(@Param("parentId") Long parentId);


}
