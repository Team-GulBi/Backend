package com.gulbi.Backend.domain.rental.application.entity;

import com.gulbi.Backend.domain.user.entity.User;
import jakarta.persistence.*;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  // 상품과 다대일 관계 설정
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)  // 사용자와 다대일 관계 설정
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;  // 상태 (reserving, using, rejected, returned)

    @Column(nullable = false)
    private LocalDate startDate;  // 시작 날짜

    @Column(nullable = false)
    private LocalDate endDate;  // 끝 날짜

    @Column(nullable = false)
    private int totalPrice;  // 총 가격

    // 시작 날짜와 끝 날짜의 차이를 계산하는 메서드
    public int calculateTotalDays() {
        if (startDate != null && endDate != null) {
            return Period.between(startDate, endDate).getDays();
        }
        return 0; // 만약 시작 또는 끝 날짜가 null인 경우 0일 반환
    }

    public Application(Product product, User user, ApplicationStatus status, LocalDate startDate, LocalDate endDate, int totalPrice) {
        this.product = product;
        this.user = user;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

}
