package com.gulbi.Backend.domain.chat.websocket;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;
import org.springframework.web.socket.messaging.StompSubProtocolHandler;

@Slf4j
@Component
public class WebSocketEventHandler {

    @PostConstruct
    public void init() {
        log.info("WebSocketEventHandler 초기화 완료");
    }

    // 연결 시 이벤트 처리
    public void handleConnect(StompHeaderAccessor headerAccessor) {
        String sessionId = headerAccessor.getSessionId();
        log.info("WebSocket 연결됨. 세션 ID: {}", sessionId);
    }

    // 연결 종료 시 이벤트 처리
    public void handleDisconnect(StompHeaderAccessor headerAccessor) {
        String sessionId = headerAccessor.getSessionId();
        log.info("WebSocket 연결 종료. 세션 ID: {}", sessionId);
    }

    // 메시지 처리 이벤트
    public void handleMessage(SimpMessageHeaderAccessor headerAccessor, Object message) {
        log.info("WebSocket 메시지 수신: {}", message);
        // 여기서 메시지 처리 로직 추가 가능 (DB 저장, 알림 등)
    }
}
