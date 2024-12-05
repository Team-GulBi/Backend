package com.gulbi.Backend.domain.chat.message.dto;

import com.gulbi.Backend.domain.chat.message.entity.ChatMessage;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatMessageDto {
    private Long id;
    private String content;
    private Long senderId;    // 보낸 사람 ID
    private Long chatRoomId;  // 채팅방 ID
    private LocalDateTime timestamp;  // 생성 시간

    // 생성자
    public ChatMessageDto(Long id, String content, Long senderId, Long chatRoomId, LocalDateTime timestamp) {
        this.id = id;
        this.content = content;
        this.senderId = senderId;
        this.chatRoomId = chatRoomId;
        this.timestamp = timestamp;
    }
    public static ChatMessageDto from(ChatMessage chatMessage) {
        return new ChatMessageDto(
                chatMessage.getId(),
                chatMessage.getContent(),
                chatMessage.getSender().getId(),
                chatMessage.getChatRoom().getId(),
                chatMessage.getTimestamp()
        );
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }
}


