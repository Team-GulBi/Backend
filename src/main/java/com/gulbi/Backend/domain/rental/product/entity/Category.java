package com.gulbi.Backend.domain.rental.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Getter
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC) // 기본 생성자 접근 제어자 설정
@AllArgsConstructor(access = AccessLevel.PUBLIC) // 모든 필드를 초기화하는 생성자 접근 제어자 설정
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    // 부모 카테고리를 설정할 수 있는 생성자
//    public Category(String name, Category parent) {
//        this.name = name;
//        this.parent = parent;
//    }
}
