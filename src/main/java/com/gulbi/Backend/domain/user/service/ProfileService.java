package com.gulbi.Backend.domain.user.service;

import com.gulbi.Backend.domain.user.dto.ProfileResponseDto;
import com.gulbi.Backend.domain.user.entity.Profile;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.dto.ProfileRequestDto;
import com.gulbi.Backend.domain.user.repository.ProfileRepository;
import com.gulbi.Backend.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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

        // DTO와 User를 사용해 Profile 인스턴스 생성
        Profile profile = Profile.fromDto(request, user);
        profileRepository.save(profile);
    }

    public void updateProfile(ProfileRequestDto request, UserDetails userDetails) {
        //User의 이메일로 해당 사용자의 프로필 조회
        String email=userDetails.getUsername();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile existingProfile = profileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        // 기존 프로필 필드를 DTO에 있는 값으로 업데이트
        existingProfile.update(request);
        profileRepository.save(existingProfile); // 수정된 프로필을 저장
    }

    // 프로필 조회
    public ProfileResponseDto getProfile(Long userId) {
        // SecurityContext에서 인증된 사용자 정보(UserDetails) 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();

        User loggedInUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Long loggedInUserId = loggedInUser.getId();

        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));

        if (userId.equals(loggedInUserId)) {
            // 본인의 프로필
            return ProfileResponseDto.fromProfileForUser(
                    profile.getImage(),
                    profile.getIntro(),
                    profile.getPhone(),
                    profile.getSignature(),
                    profile.getSido(),
                    profile.getSigungu(),
                    profile.getBname()
            );
        } else {
            // 타인의 프로필
            return ProfileResponseDto.fromProfileForOtherUsers(
                    profile.getImage(),
                    profile.getIntro(),
                    profile.getSido(),
                    profile.getSigungu(),
                    profile.getBname()
            );
        }
    }
}

