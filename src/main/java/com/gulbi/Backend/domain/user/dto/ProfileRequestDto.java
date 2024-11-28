package com.gulbi.Backend.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ProfileRequestDto {
    private final String image; // 프로필 이미지 URL
    private final String intro; // 자기소개
    private final String phone; // 전화번호
    private final String signature; // 전자서명
    private final String sido; // 시도
    private final String sigungu; // 시군구
    private final String bname; // 읍면동
}