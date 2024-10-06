package com.gulbi.Backend.domain.rental.product.entity;

import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  // 사용자와 다대일 관계 설정
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)  // 카테고리 코드 테이블과 다대일 관계 설정
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(length = 100)
    private String tag;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private int views;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false, length = 20)
    private String sido;  // 시/도

    @Column(nullable = false, length = 20)
    private String sigungu;  // 시/군/구

    @Column(length = 20)
    private String bname;  // 읍/면/동

    @Column(nullable = false, length = 500)
    private String description;  // 상품 설명

    @Column(nullable = true)
    private float rating;  // 평균 평점

    // 생성자
    @Builder
    public Product(User user, Category category, String tag, String title, String name, int views, int price, int period,
                   String sido, String sigungu, String bname, String description, float rating) {
        this.user = user;
        this.category = category;
        this.tag = tag;
        this.title = title;
        this.name = name;
        this.views = views;
        this.price = price;
        this.sido = sido;
        this.sigungu = sigungu;
        this.bname = bname;
        this.description = description;
        this.rating = rating;
    }
}
