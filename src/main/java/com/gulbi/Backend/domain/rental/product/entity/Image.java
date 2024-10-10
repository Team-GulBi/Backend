package com.gulbi.Backend.domain.rental.product.entity;

import com.gulbi.Backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@Getter
@NoArgsConstructor
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  // 사용자와 다대일 관계 설정
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(length = 100000)
    private String url;
    //데이터 타입이 너무 큰거 같아서 최적화 고려 해야함
    @Builder
    public Image(Product product, String url) {
        this.product = product;
        this.url = url;
    }
}
