package com.gulbi.Backend.domain.chat.message.service;

import com.gulbi.Backend.domain.chat.message.dto.ChatMessageDto;
import com.gulbi.Backend.domain.chat.message.entity.ChatMessage;
import com.gulbi.Backend.domain.chat.message.repository.ChatMessageRepository;
import com.gulbi.Backend.domain.chat.room.entity.ChatRoom;
import com.gulbi.Backend.domain.chat.room.service.ChatRoomService;
import com.gulbi.Backend.domain.chat.websocket.WebSocketEventHandler;
import com.gulbi.Backend.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;
    private final WebSocketEventHandler webSocketEventHandler;

    //메시지 저장 및 오프라인 사용자 처리
    public ChatMessage sendMessage(Long chatRoomId, String content, User sender) {
        // 메시지 저장
        ChatRoom chatRoom = chatRoomService.getChatRoomById(chatRoomId);
        boolean isRecipientOnline = webSocketEventHandler.isUserOnline(chatRoom.getUser2().getId()); // 상대방 온라인 여부 확인

        ChatMessage chatMessage = ChatMessage.builder()
                .content(content)
                .sender(sender)
                .chatRoom(chatRoom)
                .timestamp(LocalDateTime.now())
                .isOnline(isRecipientOnline) // 실시간 상태 반영
                .build();

        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessageDto> getMessages(Long chatRoomId) {
        return chatMessageRepository.findByChatRoomId(chatRoomId)
                .stream()
                .map(ChatMessageDto::from)
                .toList();
    }

    public void markMessageAsRead(Long messageId) {
        ChatMessage message = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("메시지를 찾을 수 없습니다."));
        message.markAsRead();
        chatMessageRepository.save(message);
    }
}
