package com.gulbi.Backend.domain.user.service;

import com.gulbi.Backend.domain.user.dto.ProfileRequestDto;
import com.gulbi.Backend.domain.user.dto.ProfileResponseDto;
import com.gulbi.Backend.domain.user.entity.Profile;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.exception.ProfileNotFoundException;
import com.gulbi.Backend.domain.user.exception.UserNotFoundException;
import com.gulbi.Backend.domain.user.repository.ProfileRepository;
import com.gulbi.Backend.domain.user.repository.UserRepository;
import com.gulbi.Backend.global.util.JwtUtil;
import com.gulbi.Backend.global.util.SecurityUtil;
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

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository, JwtUtil jwtUtil,UserService userService) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.userService=userService;
    }
    public void createProfile(ProfileRequestDto request, UserDetails userDetails) {
        // 이메일을 통해 User 객체를 찾기
        User user = getUserByEmail(userDetails.getUsername());
        Profile profile = Profile.fromDto(request, user);
        profileRepository.save(profile);
    }

    public String updateProfile(ProfileRequestDto request) {
        UserDetails userDetails = SecurityUtil.getAuthenticatedUser();
        User user = getUserByEmail(userDetails.getUsername());
        Profile existingProfile = getProfileByUser(user);
        // 프로필 업데이트
        existingProfile.update(request);
        profileRepository.save(existingProfile);
        // 프로필 완료 상태에 따라 역할을 결정하고 토큰 생성
        String role = determineUserRole(existingProfile);
        String token = generateUserToken(user, role);
        return token;
    }



    // 프로필 조회
    public ProfileResponseDto getProfile(Long userId) {
        UserDetails userDetails = SecurityUtil.getAuthenticatedUser();
        User loggedInUser = getUserByEmail(userDetails.getUsername());
        Long loggedInUserId = loggedInUser.getId();

        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));

        if (userId.equals(loggedInUserId)) {
            return ProfileResponseDto.fromProfileForUser(
                    profile.getImage(), profile.getIntro(), profile.getPhone(),
                    profile.getSignature(), profile.getSido(), profile.getSigungu(), profile.getBname());
        } else {
            return ProfileResponseDto.fromProfileForOtherUsers(
                    profile.getImage(), profile.getIntro(), profile.getSido(),
                    profile.getSigungu(), profile.getBname());
        }
    }
    
    public String getProfileImage(Long userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found for user ID: " + userId));
        return profile.getImage();
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    private Profile getProfileByUser(User user) {
        return profileRepository.findByUser(user)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));
    }

    private UserDetails getAuthenticatedUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }

    private String determineUserRole(Profile profile) {
        return userService.isProfileComplete(profile) ? "ROLE_COMPLETED_USER" : "ROLE_INCOMPLETED_USER";
    }

    private String generateUserToken(User user, String role) {
        return jwtUtil.generateToken(user.getEmail(), user.getId(), role);
    }



}

