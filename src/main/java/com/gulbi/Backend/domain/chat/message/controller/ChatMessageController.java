package com.gulbi.Backend.domain.chat.message.controller;

import com.gulbi.Backend.domain.chat.message.dto.ChatMessageDto;
import com.gulbi.Backend.domain.chat.message.entity.ChatMessage;
import com.gulbi.Backend.domain.chat.message.service.ChatMessageService;
import com.gulbi.Backend.domain.chat.room.service.ChatRoomService;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;
    private final UserService userService;
    private final SimpMessageSendingOperations messagingTemplate;


    // 채팅방 메시지 목록 조회
    @GetMapping("/{chatRoomId}")
    public ResponseEntity<List<ChatMessageDto>> getMessages(@PathVariable Long chatRoomId) {
        List<ChatMessageDto> messages = chatMessageService.getMessages(chatRoomId);
        return ResponseEntity.ok(messages);
    }

    // 메시지 읽음 처리
    @PatchMapping("/{messageId}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long messageId) {
        chatMessageService.markMessageAsRead(messageId);
        return ResponseEntity.noContent().build();
    }

    // 웹소켓 메시지 처리
    // /pub/chat/message로 메시지를 보내고, /sub/chat/room/{chatRoomId}로 받는 구조.
    @MessageMapping("/chat/message")
    public void sendMessage(ChatMessageDto messageDto) {
        log.info("WebSocket 메시지 처리: {}", messageDto);

        // 1. 현재 로그인된 사용자 가져오기
        User sender = userService.getAuthenticatedUser();

        // 2. 메시지 저장 (DB 저장)
        ChatMessage savedMessage = chatMessageService.sendMessage(
                messageDto.getChatRoomId(),
                messageDto.getContent(),
                sender
        );

        // 3. 특정 구독자들에게 메시지 전달
        messagingTemplate.convertAndSend(
                "/sub/chat/room/" + messageDto.getChatRoomId(),
                ChatMessageDto.from(savedMessage) // 저장된 메시지를 DTO로 변환 후 전달
        );
    }
}