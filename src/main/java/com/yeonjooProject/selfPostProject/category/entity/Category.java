package com.yeonjooProject.selfPostProject.category.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // 카테고리 이름

    private Integer displayOrder; // 정렬 순서

    private String type; // 게시판 타입

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = true)
    private Category parent; // 부모 카테고리

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> children = new ArrayList<>(); // 자식 카테고리

    public void update(String name, Integer displayOrder, String type) {
        this.name = name;
        this.displayOrder = displayOrder;
        this.type = type;
    }

    // displayOrder 설정 메서드 추가
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

}
