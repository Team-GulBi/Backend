package com.gulbi.Backend.domain.chat.websocket;

import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class WebSocketEventHandler {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    private static final Map<Long, String> onlineUsers = new ConcurrentHashMap<>();

    public WebSocketEventHandler(UserRepository userRepository, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    @PostConstruct
    public void init() {
        log.info("WebSocketEventHandler 초기화 완료");
    }

    // 웹소켓 연결 이벤트 처리
    public void handleConnect(StompHeaderAccessor headerAccessor) {
        Long userId = getUserIdFromHeader(headerAccessor);
        if (userId != null) {
            onlineUsers.put(userId, headerAccessor.getSessionId());
            log.info("WebSocket 연결됨. 사용자 ID: {}, 세션 ID: {}", userId, headerAccessor.getSessionId());

            // 연결되었으니 메시지 소비 이벤트 발행
            eventPublisher.publishEvent(new UserConnectedEvent(userId));
        } else {
            log.warn("WebSocket 연결 실패: 사용자 ID가 null입니다.");
        }
    }

    public void handleDisconnect(StompHeaderAccessor headerAccessor) {
        Long userId = getUserIdFromHeader(headerAccessor);
        if (userId != null) {
            onlineUsers.remove(userId);
            log.info("WebSocket 연결 종료. 사용자 ID: {}", userId);
        }
    }

    private Long getUserIdFromHeader(StompHeaderAccessor headerAccessor) {
        if (headerAccessor.getUser() != null) {
            Object principal = headerAccessor.getUser();
            if (principal instanceof org.springframework.security.core.userdetails.User userDetails) {
                String email = userDetails.getUsername();
                return userRepository.findByEmail(email)
                        .map(User::getId)
                        .orElse(null);
            }
        }
        return null;
    }

    public boolean isUserOnline(Long userId) {
        return onlineUsers.containsKey(userId);
    }
}
