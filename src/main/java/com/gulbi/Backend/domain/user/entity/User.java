package com.gulbi.Backend.domain.user.entity;

import com.gulbi.Backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor  // 모든 필드를 사용하는 생성자
@Builder  // 빌더 패턴을 위한 어노테이션
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;
}
