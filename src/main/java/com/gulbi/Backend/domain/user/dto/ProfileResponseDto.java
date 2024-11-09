package com.gulbi.Backend.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileResponseDto {
    private Long id;
    private String profileImage;
    private String introduction;
    private String phoneNumber;
    private String signature;
    private String city;
    private String district;
    private String neighborhood;
}
