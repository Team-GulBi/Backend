package com.gulbi.Backend.domain.chat.message.service;

import com.gulbi.Backend.domain.chat.message.dto.ChatMessageDto;
import com.gulbi.Backend.domain.chat.message.entity.ChatMessage;
import com.gulbi.Backend.domain.chat.message.repository.ChatMessageRepository;
import com.gulbi.Backend.domain.chat.room.entity.ChatRoom;
import com.gulbi.Backend.domain.chat.room.service.ChatRoomService;
import com.gulbi.Backend.domain.chat.websocket.WebSocketEventHandler;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.global.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;
    private final WebSocketEventHandler webSocketEventHandler;
    private final RabbitTemplate rabbitTemplate;

    // 메시지 저장 및 오프라인 사용자 처리
    public ChatMessage sendMessage(Long chatRoomId, String content, User sender) {
        // 채팅방 정보 가져오기
        ChatRoom chatRoom = chatRoomService.getChatRoomById(chatRoomId);

        // 상대방이 현재 채팅방에 있는지 확인 (user1과 user2 비교)
        boolean isRecipientOnline = checkRecipientOnline(chatRoom, sender);

        // 메시지 생성 및 저장
        ChatMessage chatMessage = ChatMessage.builder()
                .content(content)
                .sender(sender)
                .chatRoom(chatRoom)
                .timestamp(LocalDateTime.now())
                .isOnline(isRecipientOnline)
                .build();

        ChatMessage savedMessage = chatMessageRepository.save(chatMessage);

        // 상대방이 오프라인일 경우 메시지를 RabbitMQ 큐에 추가
        if (!isRecipientOnline) {
            sendMessageToQueue(ChatMessageDto.from(savedMessage));
        }

        return savedMessage;
    }

    // 특정 채팅방에 있는 메시지 목록 가져오기
    public List<ChatMessageDto> getMessages(Long chatRoomId) {
        return chatMessageRepository.findByChatRoomId(chatRoomId)
                .stream()
                .map(ChatMessageDto::from)  // ChatMessage -> ChatMessageDto 변환
                .toList();
    }

    // 메시지를 읽음 상태로 변경
    public void markMessageAsRead(Long messageId) {
        ChatMessage message = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("메시지를 찾을 수 없습니다."));
        message.markAsRead();
        chatMessageRepository.save(message);
    }

    // RabbitMQ 큐에 메시지 전송
    private void sendMessageToQueue(ChatMessageDto chatMessageDto) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, chatMessageDto);
        log.info("Sent Message to Queue: {}", chatMessageDto);
    }

    // 상대방이 온라인인지 확인 (sender와 반대되는 사용자의 상태 확인)
    private boolean checkRecipientOnline(ChatRoom chatRoom, User sender) {
        if (chatRoom.getUser1().getId().equals(sender.getId())) {
            return webSocketEventHandler.isUserOnline(chatRoom.getUser2().getId());
        } else {
            return webSocketEventHandler.isUserOnline(chatRoom.getUser1().getId());
        }
    }
}
