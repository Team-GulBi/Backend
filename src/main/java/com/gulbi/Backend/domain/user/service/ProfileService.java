package com.gulbi.Backend.domain.user.service;

import com.gulbi.Backend.domain.user.entity.Profile;
import com.gulbi.Backend.domain.user.entity.User; // User import 추가
import com.gulbi.Backend.domain.user.dto.ProfileRequestDto;
import com.gulbi.Backend.domain.user.repository.ProfileRepository;
import com.gulbi.Backend.domain.user.repository.UserRepository; // UserRepository import 추가
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public void createProfile(ProfileRequestDto request, UserDetails userDetails) {
        // 이메일을 통해 User 객체를 찾기
        String email = userDetails.getUsername(); // UserDetails에서 이메일 추출
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 프로필 객체 생성 및 저장
        Profile profile = Profile.builder()
                .user(user)
                .image(request.getImage())
                .intro(request.getIntro())
                .phone(request.getPhone())
                .signature(request.getSignature())
                .sido(request.getSido())
                .sigungu(request.getSigungu())
                .bname(request.getBname())
                .build();

        profileRepository.save(profile);
    }
}