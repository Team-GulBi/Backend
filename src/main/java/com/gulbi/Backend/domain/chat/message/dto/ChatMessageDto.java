package com.gulbi.Backend.domain.chat.message.dto;

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

    // Getter, Setter
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}


