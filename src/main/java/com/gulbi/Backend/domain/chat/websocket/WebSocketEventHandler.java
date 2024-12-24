package com.gulbi.Backend.domain.chat.websocket;
import com.gulbi.Backend.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.context.ApplicationListener;
import com.gulbi.Backend.domain.user.entity.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class WebSocketEventHandler implements ApplicationListener<SessionConnectEvent> {

    private final UserRepository userRepository;
    private static final Map<Long, String> onlineUsers = new ConcurrentHashMap<>();
    private static final Map<String, Long> sessionUserMap = new ConcurrentHashMap<>();

    public WebSocketEventHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName(); // 사용자 이메일
            log.info("WebSocket 연결된 사용자: {}", username);

            Long userId = userRepository.findByEmail(username).get().getId();
            if (userId != null) {
                // 세션 ID를 통해 해당 세션에 연결된 사용자 ID를 매핑
                String sessionId = headerAccessor.getSessionId();
                sessionUserMap.put(sessionId, userId);
                log.info("세션 ID: {}로 사용자 ID {} 추가", sessionId, userId);
            } else {
                log.warn("사용자 ID를 찾을 수 없음.");
            }
        } else {
            log.warn("WebSocket 연결 실패: 인증되지 않은 사용자");
        }
    }
    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();

        // 세션에서 사용자 ID를 가져옴
        sessionUserMap.remove(sessionId); // 연결 종료된 세션의 사용자 ID를 제거

        log.info("WebSocket 종료된 세션 ID: {}",sessionId);
    }


    public boolean isUserOnline(Long userId) {
        return sessionUserMap.containsValue(userId);
    }
}
