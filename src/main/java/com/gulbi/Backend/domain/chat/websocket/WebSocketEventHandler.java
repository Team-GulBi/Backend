package com.gulbi.Backend.domain.chat.websocket;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class WebSocketEventHandler {

    // 세션 ID와 사용자 ID 매핑 (스레드 안전)
    private static final Map<Long, String> onlineUsers = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        log.info("WebSocketEventHandler 초기화 완료");
    }

    // 연결 시 이벤트 처리
    public void handleConnect(StompHeaderAccessor headerAccessor) {
        // headerAccessor.getUser()를 통해 사용자 정보 접근
        Long userId = getUserIdFromHeader(headerAccessor);  // 사용자 ID 추출
        if (userId != null) {
            onlineUsers.put(userId, headerAccessor.getSessionId());  // 세션 ID와 사용자 ID 저장
            log.info("WebSocket 연결됨. 사용자 ID: {}, 세션 ID: {}", userId, headerAccessor.getSessionId());
        }
    }

    // 연결 종료 시 이벤트 처리
    public void handleDisconnect(StompHeaderAccessor headerAccessor) {
        Long userId = getUserIdFromHeader(headerAccessor);  // 사용자 ID 추출
        if (userId != null) {
            onlineUsers.remove(userId);  // 사용자 세션 종료 시 온라인 리스트에서 제거
            log.info("WebSocket 연결 종료. 사용자 ID: {}", userId);
        }
    }

    // 온라인 상태 확인 메서드
    public boolean isUserOnline(Long userId) {
        return onlineUsers.containsKey(userId);  // 온라인 상태 확인 (Map에 존재하는지 확인)
    }

    // 사용자 ID 추출 메서드 (headerAccessor에서 사용자 ID를 안전하게 가져오는 방법)
    private Long getUserIdFromHeader(StompHeaderAccessor headerAccessor) {
        if (headerAccessor.getUser() != null) {
            try {
                return Long.valueOf(headerAccessor.getUser().getName());
            } catch (NumberFormatException e) {
                log.error("사용자 ID 형식 오류: {}", headerAccessor.getUser().getName());
            }
        }
        return null;
    }
}
