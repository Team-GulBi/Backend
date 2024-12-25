package com.gulbi.Backend.global.config;

import com.gulbi.Backend.domain.chat.message.dto.ChatMessageDto;
import com.gulbi.Backend.domain.chat.room.entity.ChatRoom;
import com.gulbi.Backend.domain.chat.room.service.ChatRoomService;
import com.gulbi.Backend.domain.chat.websocket.WebSocketEventHandler;
import com.gulbi.Backend.domain.chat.websocket.UserConnectedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageListener {

    private final SimpMessageSendingOperations messagingTemplate;
    private final RabbitTemplate rabbitTemplate;
    private final WebSocketEventHandler webSocketEventHandler;
    private final ChatRoomService chatRoomService;

    // 중복 메시지 방지를 위한 처리 상태 추적
    private final Set<Long> processedMessages = new HashSet<>();

//    // 메시지 수신 및 처리
//    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
//    public void receiveMessage(ChatMessageDto chatMessageDto) {
//        if (chatMessageDto == null) {
//            log.error("Received null ChatMessageDto");
//            return;
//        }
//
//        Long senderId = chatMessageDto.getSenderId();
//        Long chatRoomId = chatMessageDto.getChatRoomId();
//        Long receiverId = findReceiverIdFromChatRoom(chatRoomId, senderId);
//
//        chatMessageDto.setReceiverId(receiverId);
//
//        // 이미 처리된 메시지인지 확인 (중복 방지)
//        if (processedMessages.contains(chatMessageDto.getId())) {
//            log.info("Message {} already processed, skipping.", chatMessageDto.getId());
//            return;
//        }
//
//        // 수신자가 온라인 상태인지 확인
//        boolean isReceiverOnline = webSocketEventHandler.isUserOnline(receiverId);
//        log.info("Receiver {} isOnline: {}", receiverId, isReceiverOnline);
//
//        if (isReceiverOnline) {
//            // Receiver가 온라인 상태일 때 WebSocket으로 메시지 전송
//            log.info("Receiver {} is online. Sending message: {}", receiverId, chatMessageDto);
//            sendToWebSocket(chatMessageDto);
//        } else {
//            // Receiver가 오프라인 상태일 때 메시지를 큐에 저장
//            log.info("Receiver {} is offline. Storing message for later: {}", receiverId, chatMessageDto);
//            storeMessageForLater(chatMessageDto);
//        }
//
//        // 큐에서 메시지를 처리한 후에 processedMessages에 추가
//        processedMessages.add(chatMessageDto.getId());
//    }

    // 사용자 연결 이벤트 처리
    @EventListener
    public void onUserConnected(UserConnectedEvent event) {
        Long userId = event.getUserId();
        log.info("User {} connected. Processing queued messages.", userId);

        ChatMessageDto chatMessageDto;
        while ((chatMessageDto = (ChatMessageDto) rabbitTemplate.receiveAndConvert(RabbitMQConfig.QUEUE_NAME)) != null) {
            log.debug("Dequeued message: {}", chatMessageDto);

            // 채팅방에서 상대방 ID 가져오기
            Long receiverId = findReceiverIdFromChatRoom(chatMessageDto.getChatRoomId(), chatMessageDto.getSenderId());
            chatMessageDto.setReceiverId(receiverId);

            // 수신자가 현재 연결된 사용자이고, 메시지가 처리되지 않았다면 전송
            if (receiverId.equals(userId) && !processedMessages.contains(chatMessageDto.getId())) {
                log.info("Delivering queued message to user {}: {}", userId, chatMessageDto);
                sendToWebSocket(chatMessageDto);
                processedMessages.add(chatMessageDto.getId());
            } else {
                log.warn("Message for user {} does not match connected user {}. Requeuing message: {}",
                        receiverId, userId, chatMessageDto);
                storeMessageForLater(chatMessageDto);
            }
        }
        log.info("Finished processing queued messages for user {}.", userId);
    }

    // WebSocket으로 메시지 전송
    private void sendToWebSocket(ChatMessageDto chatMessageDto) {
        log.debug("Sending message via WebSocket to chat room {}: {}", chatMessageDto.getChatRoomId(), chatMessageDto);
        messagingTemplate.convertAndSend(
                "/sub/chat/room/" + chatMessageDto.getChatRoomId(),
                chatMessageDto
        );
        log.info("Message sent to WebSocket for chat room {}: {}", chatMessageDto.getChatRoomId(), chatMessageDto);
    }

    // 메시지를 큐에 저장
    private void storeMessageForLater(ChatMessageDto chatMessageDto) {
        log.debug("Storing message back in queue: {}", chatMessageDto);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, chatMessageDto);
        log.info("Message requeued: {}", chatMessageDto);
    }



    // 채팅방에서 상대방 ID 찾기
    private Long findReceiverIdFromChatRoom(Long chatRoomId, Long senderId) {
        ChatRoom chatRoom = chatRoomService.findById(chatRoomId)
                .orElseThrow(() -> new IllegalArgumentException("ChatRoom not found with id: " + chatRoomId));

        if (chatRoom.getUser1().getId().equals(senderId)) {
            return chatRoom.getUser2().getId();
        } else if (chatRoom.getUser2().getId().equals(senderId)) {
            return chatRoom.getUser1().getId();
        } else {
            throw new IllegalArgumentException("SenderId does not match any users in the chat room");
        }
    }
}
