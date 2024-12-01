package com.gulbi.Backend.global.config;

import com.gulbi.Backend.domain.chat.message.dto.ChatMessageDto;
import com.gulbi.Backend.domain.chat.message.entity.ChatMessage;
import com.gulbi.Backend.domain.chat.websocket.WebSocketEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageListener {

    private final SimpMessageSendingOperations messagingTemplate;  // 메시지 전송을 위한 WebSocket 템플릿
    private final RabbitTemplate rabbitTemplate;  // RabbitMQ 메시지 전송용
    private final WebSocketEventHandler webSocketEventHandler;  // WebSocket 이벤트 처리 (온라인 상태 확인)

    // RabbitMQ 큐에서 메시지를 받아서 처리
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(ChatMessage chatMessage) {
        // 사용자가 온라인일 때만 메시지를 WebSocket으로 전송
        if (isUserOnline(chatMessage.getChatRoom().getUser1().getId())) {
            sendToWebSocket(chatMessage);
        } else if (isUserOnline(chatMessage.getChatRoom().getUser2().getId())) {
            sendToWebSocket(chatMessage);
        } else {
            // 사용자가 여전히 오프라인이라면 큐에서 계속 대기시킬 수 있도록 처리
            rabbitTemplate.convertAndSend("chat-queue", chatMessage);  // 오프라인 사용자일 경우 다시 큐에 넣기
        }
    }

    // WebSocket을 통해 메시지 전송
    private void sendToWebSocket(ChatMessage chatMessage) {
        messagingTemplate.convertAndSend(
                "/sub/chat/room/" + chatMessage.getChatRoom().getId(),
                new ChatMessageDto(
                        chatMessage.getId(),
                        chatMessage.getContent(),
                        chatMessage.getSender().getId(),
                        chatMessage.getChatRoom().getId(),
                        chatMessage.getTimestamp()
                )
        );
    }

    // 사용자가 온라인 상태인지 확인하는 메서드
    private boolean isUserOnline(Long userId) {
        return webSocketEventHandler.isUserOnline(userId);  // WebSocket 상태 체크 (WebSocketEventHandler에 포함됨)
    }

    // RabbitMQ 큐에 메시지를 전송
    public void sendMessageToQueue(ChatMessage chatMessage) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, chatMessage);  // 큐에 메시지 보내기
    }
}