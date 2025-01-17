package com.gulbi.Backend.domain.rental.product.entity;

import com.gulbi.Backend.domain.rental.product.code.ProductErrorCode;
import com.gulbi.Backend.domain.rental.product.exception.ProductException;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  // 사용자와 다대일 관계 설정
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)  // 대분류와 다대일 관계 설정
    @JoinColumn(name = "b_category_id", nullable = false) // 외래 키 이름 수정
    private Category bCategory;

    @ManyToOne(fetch = FetchType.LAZY)  // 중분류와 다대일 관계 설정
    @JoinColumn(name = "m_category_id", nullable = false) // 외래 키 이름 수정
    private Category mCategory;

    @ManyToOne(fetch = FetchType.LAZY)  // 소분류와 다대일 관계 설정
    @JoinColumn(name = "s_category_id", nullable = false) // 외래 키 이름 수정
    private Category sCategory;

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

    @Column(nullable = true)
    private String mainImage;

    // 생성자
    @Builder
    private Product(User user, Category bCategory, Category mCategory, Category sCategory, String tag, String title, String name, int views, int price,
                   String sido, String sigungu, String bname, String description, float rating, String mainImage) {

        if (user == null) {
            throw new ProductException.MissingProductFieldException(ProductErrorCode.MISSING_USER);
        }
        if (bCategory == null) {
            throw new ProductException.MissingProductFieldException(ProductErrorCode.MISSING_CATEGORY);
        }
        if (mCategory == null) {
            throw new ProductException.MissingProductFieldException(ProductErrorCode.MISSING_CATEGORY);
        }
        if (sCategory == null) {
            throw new ProductException.MissingProductFieldException(ProductErrorCode.MISSING_CATEGORY);
        }
        if (title == null || title.isEmpty()) {
            throw new ProductException.MissingProductFieldException(ProductErrorCode.MISSING_TITLE);
        }
        if (name == null || name.isEmpty()) {
            throw new ProductException.MissingProductFieldException(ProductErrorCode.MISSING_NAME);
        }
        if (views < 0) {
            throw new ProductException.MissingProductFieldException(ProductErrorCode.INVALID_VIEWS);
        }
        if (price < 0) {
            throw new ProductException.MissingProductFieldException(ProductErrorCode.INVALID_PRICE);
        }
        if (sido == null || sido.isEmpty()) {
            throw new ProductException.MissingProductFieldException(ProductErrorCode.MISSING_SIDO);
        }
        if (sigungu == null || sigungu.isEmpty()) {
            throw new ProductException.MissingProductFieldException(ProductErrorCode.MISSING_SIGUNGU);
        }
        if (description == null || description.isEmpty()) {
            throw new ProductException.MissingProductFieldException(ProductErrorCode.MISSING_DESCRIPTION);
        }

        this.user = user;
        this.bCategory = bCategory;
        this.mCategory = mCategory;
        this.sCategory = sCategory;
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
        this.mainImage = mainImage;
    }
}
