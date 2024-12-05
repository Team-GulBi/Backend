package com.gulbi.Backend.global.config;

import com.gulbi.Backend.domain.chat.message.dto.ChatMessageDto;
import com.gulbi.Backend.domain.chat.websocket.UserConnectedEvent;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageListener {

    private final SimpMessageSendingOperations messagingTemplate;
    private final RabbitTemplate rabbitTemplate;

    // 이미 처리된 메시지 ID를 저장할 Set
    private final Set<Long> processedMessageIds = new HashSet<>();

    // 온라인 사용자 상태 관리 (예: 사용자 ID와 상태 저장)
    private final Map<Long, Boolean> onlineUsers = new HashMap<>();

    // 큐에서 메시지 수신 (수동 ACK 적용)
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(ChatMessageDto chatMessageDto, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {
            // 중복 메시지 확인
            if (processedMessageIds.contains(chatMessageDto.getId())) {
                log.info("Duplicate message detected, skipping: {}", chatMessageDto.getId());
                channel.basicAck(tag, false);  // 중복 메시지 ACK 처리
                return;
            }

            // 메시지를 Set에 추가하여 처리된 것으로 표시
            processedMessageIds.add(chatMessageDto.getId());
            log.info("Received Message: {}", chatMessageDto);

            // 사용자 온라인 상태 확인 후 메시지 전송
            if (onlineUsers.getOrDefault(chatMessageDto.getSenderId(), false)) {
                sendToWebSocket(chatMessageDto);  // 온라인 사용자에게 즉시 전송
            } else {
                storeMessageForLater(chatMessageDto);  // 오프라인 사용자에 대한 메시지 저장
            }

            channel.basicAck(tag, false);  // 메시지 처리 완료 후 ACK
        } catch (Exception e) {
            log.error("Error processing message: {}, retrying... {}", chatMessageDto.getId(), e.getMessage());
            try {
                channel.basicNack(tag, false, true);  // 처리 실패 시 NACK 및 재처리
            } catch (IOException ioException) {
                log.error("Failed to NACK message: {}", ioException.getMessage());
            }
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
                if (chatMessageDto.getChatRoomId().equals(userId)) {
                    sendToWebSocket(chatMessageDto);
                } else {
                    requeueMessage(chatMessageDto);
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
        try {
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, chatMessageDto);
            log.info("Stored message for user {} to be sent later.", chatMessageDto.getChatRoomId());
        } catch (Exception e) {
            log.error("Failed to store message for user {}: {}", chatMessageDto.getChatRoomId(), e.getMessage());
        }
    }

    // 메시지를 다시 큐에 넣는 로직
    private void requeueMessage(ChatMessageDto chatMessageDto) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, chatMessageDto);
        log.info("Requeued message for user {}.", chatMessageDto.getChatRoomId());
    }
}
