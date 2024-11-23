package com.gulbi.Backend.domain.user.service;

import com.gulbi.Backend.domain.user.dto.LoginRequestDto;
import com.gulbi.Backend.domain.user.dto.RegisterRequestDto;
import com.gulbi.Backend.global.util.JwtUtil;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterRequestDto request){
        User user = User.builder()
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .build();
        userRepository.save(user);
    }

    public String login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return jwtUtil.generateToken(user.getEmail(), user.getId()  );
    }

    public User getAuthenticatedUser() {
        String email = getAuthenticatedEmail();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Authenticated User not found"));
    }
    public User getDummyUser() {
        // 유니크한 email을 생성하기 위해 UUID를 사용
        String uniqueEmail = "user_" + UUID.randomUUID().toString() + "@example.com";
        String uniquePhone = "010" + UUID.randomUUID().toString() + "5680";
        String uniqueName = "김" + UUID.randomUUID().toString();
        String uniquePassWord = "dsd" + UUID.randomUUID().toString();

        User user = User.builder()
                .email(uniqueEmail)  // 유니크한 email을 사용
                .phoneNumber(uniquePhone)
                .nickname(uniqueName)
                .password(uniquePassWord)
                .build();
        userRepository.save(user);
        return user;
    }
    private String getAuthenticatedEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            throw new RuntimeException("No authenticated user");
        }
    }
}