package com.gulbi.Backend.domain.chat.websocket;

import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class WebSocketEventHandler {
    private final UserRepository userRepository;
    public WebSocketEventHandler(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    // 세션 ID와 사용자 ID 매핑 (스레드 안전)
    private static final Map<Long, String> onlineUsers = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        log.info("WebSocketEventHandler 초기화 완료");
    }

    // 연결 시 이벤트 처리
    public void handleConnect(StompHeaderAccessor headerAccessor) {
        Long userId = getUserIdFromHeader(headerAccessor);
        if (userId != null) {
            onlineUsers.put(userId, headerAccessor.getSessionId());  // 세션 ID와 사용자 ID 저장
            log.info("WebSocket 연결됨. 사용자 ID: {}, 세션 ID: {}", userId, headerAccessor.getSessionId());
        } else {
            log.warn("WebSocket 연결 실패: 사용자 ID가 null입니다.");
        }
    }

    // 연결 종료 시 이벤트 처리
    public void handleDisconnect(StompHeaderAccessor headerAccessor) {
        Long userId = getUserIdFromHeader(headerAccessor);
        if (userId != null) {
            onlineUsers.remove(userId);  // 온라인 리스트에서 제거
            log.info("WebSocket 연결 종료. 사용자 ID: {}", userId);
        }
    }

    // 온라인 상태 확인 메서드
    public boolean isUserOnline(Long userId) {
        return onlineUsers.containsKey(userId);
    }

    // 사용자 ID 추출 메서드 (예외 처리 강화)
    private Long getUserIdFromHeader(StompHeaderAccessor headerAccessor) {
        if (headerAccessor.getUser() != null) {
            Object principal = headerAccessor.getUser();

            if (principal instanceof org.springframework.security.core.userdetails.User userDetails) {
                String email = userDetails.getUsername();
                try {
                    // 이메일을 기반으로 User 엔티티에서 ID 조회
                    return userRepository.findByEmail(email)
                            .map(User::getId)
                            .orElseThrow(() -> new RuntimeException("사용자 ID를 찾을 수 없습니다. 이메일: " + email));
                } catch (Exception e) {
                    log.error("사용자 ID 조회 오류: {}", email, e);
                }
            } else {
                log.warn("Principal이 예상한 타입이 아닙니다: {}", principal.getClass().getName());
            }
        } else {
            log.warn("사용자 정보가 null입니다.");
        }
        return null;
    }


}