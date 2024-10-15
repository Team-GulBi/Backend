package com.gulbi.Backend.domain.user.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 프로필 ID (PK)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull // User가 반드시 필요
    private User user; // 사용자의 외래키 (FK)

    private String image; // 프로필 이미지 URL
    private String intro; // 자기소개
    private String phone; // 전화번호
    private String signature; // 전자서명

    @Size(max = 100) // 시도 최대 길이 제한
    private String sido; // 시도

    @Size(max = 100) // 시군구 최대 길이 제한
    private String sigungu; // 시군구

    @Size(max = 100) // 읍면동 최대 길이 제한
    private String bname; // 읍면동
}
