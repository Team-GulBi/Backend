package com.gulbi.Backend.domain.user.service;

import com.gulbi.Backend.domain.user.dto.ProfileRequestDto;
import com.gulbi.Backend.domain.user.dto.ProfileResponseDto;
import com.gulbi.Backend.domain.user.entity.Profile;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.repository.ProfileRepository;
import com.gulbi.Backend.domain.user.repository.UserRepository;
import com.gulbi.Backend.global.util.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public ProfileService(ProfileRepository profileRepository, UserRepository useRepository, JwtUtil jwtUtil,UserService userService) {
        this.profileRepository = profileRepository;
        this.userRepository = useRepository;
        this.jwtUtil = jwtUtil;
        this.userService=userService;
    }
    public void createProfile(ProfileRequestDto request, UserDetails userDetails) {
        // 이메일을 통해 User 객체를 찾기
        String email = userDetails.getUsername(); // UserDetails에서 이메일 추출
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // DTO와 User를 사용해 Profile 인스턴스 생성
        Profile profile = Profile.fromDto(request, user);
        profileRepository.save(profile);
    }

    public String updateProfile(ProfileRequestDto request, UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile existingProfile = profileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        // 프로필 업데이트
        existingProfile.update(request);
        profileRepository.save(existingProfile);

        // 프로필 완료 상태에 따라 역할을 결정하고 토큰 생성
        String role = userService.isProfileComplete(existingProfile) ? "ROLE_COMPLETED_USER" : "ROLE_INCOMPLETED_USER";
        return jwtUtil.generateToken(user.getEmail(), user.getId(), role); // 토큰 반환
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

