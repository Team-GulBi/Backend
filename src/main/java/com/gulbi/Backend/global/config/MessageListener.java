package com.gulbi.Backend.global.config;

import com.gulbi.Backend.domain.chat.message.dto.ChatMessageDto;
import com.gulbi.Backend.domain.chat.websocket.UserConnectedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageListener {

    private final SimpMessageSendingOperations messagingTemplate;
    private final RabbitTemplate rabbitTemplate;

    // 온라인 사용자 목록
    private final Map<Long, Boolean> onlineUsers = new HashMap<>();

    // 큐에서 메시지 수신
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(ChatMessageDto chatMessageDto) {
        Long senderId = chatMessageDto.getSenderId();

        // 사용자 온라인 상태 확인
        if (onlineUsers.getOrDefault(senderId, false)) {
            // 온라인 상태면 WebSocket으로 메시지 전송
            log.info("User {} is online. Sending message to WebSocket: {}", senderId, chatMessageDto);
            sendToWebSocket(chatMessageDto);
        } else {
            // 오프라인 상태면 메시지 큐에 저장
            log.info("User {} is offline. Storing message for later delivery: {}", senderId, chatMessageDto);
            storeMessageForLater(chatMessageDto);
        }
    }

    // 사용자 연결 이벤트 처리 (온라인 상태로 변경)
    @EventListener
    public void onUserConnected(UserConnectedEvent event) {
        Long userId = event.getUserId();
        onlineUsers.put(userId, true);  // 사용자를 온라인 상태로 설정
        log.info("User {} is now online.", userId);

        // 온라인 상태로 변경된 사용자에게 큐에 보관된 메시지 전달
        receiveMessageFromQueueForUser(userId);
    }

    // 사용자 연결 해제 이벤트 처리 (오프라인 상태로 변경)
    @EventListener
    public void onUserDisconnected(UserConnectedEvent event) {
        Long userId = event.getUserId();
        onlineUsers.put(userId, false);  // 사용자를 오프라인 상태로 설정
        log.info("User {} is now offline.", userId);
    }

    // 오프라인 상태의 사용자에게 큐에서 보관된 메시지 전송
    public void receiveMessageFromQueueForUser(Long userId) {
        boolean hasMoreMessages = true;

        while (hasMoreMessages) {
            ChatMessageDto chatMessageDto = (ChatMessageDto) rabbitTemplate.receiveAndConvert(RabbitMQConfig.QUEUE_NAME);

            if (chatMessageDto != null) {
                Long receiverId = chatMessageDto.getChatRoomId();  // 수신자 확인
                if (receiverId.equals(userId)) {
                    sendToWebSocket(chatMessageDto);
                } else {
                    log.info("Requeuing message for user {}.", receiverId);
                    requeueMessage(chatMessageDto);  // 수신자 아닌 경우 재큐잉
                }
            } else {
                hasMoreMessages = false;
            }
        }
    }

    // 메시지를 WebSocket으로 전송
    private void sendToWebSocket(ChatMessageDto chatMessageDto) {
        messagingTemplate.convertAndSend(
                "/sub/chat/room/" + chatMessageDto.getChatRoomId(),
                chatMessageDto
        );
    }

    // 오프라인 사용자의 메시지를 큐에 저장
    private void storeMessageForLater(ChatMessageDto chatMessageDto) {
        log.info("Storing message for later delivery: {}", chatMessageDto);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, chatMessageDto);
    }

    // 메시지를 다시 큐에 넣는 로직
    private void requeueMessage(ChatMessageDto chatMessageDto) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, chatMessageDto);
    }
}
