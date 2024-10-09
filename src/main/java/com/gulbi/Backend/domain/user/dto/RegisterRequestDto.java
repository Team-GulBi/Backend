package com.gulbi.Backend.domain.user.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class RegisterRequestDto {
    private String nickname;
    private String email;
    private String password;
    private String phoneNumber;
}
