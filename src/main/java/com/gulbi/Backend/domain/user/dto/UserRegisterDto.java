package com.gulbi.Backend.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDto {
    private String nickname;
    private String email;
    private String password;
}
