package com.gulbi.Backend.domain.chat.message.controller;

import com.gulbi.Backend.domain.chat.message.dto.ChatMessageDto;
import com.gulbi.Backend.domain.chat.message.entity.ChatMessage;
import com.gulbi.Backend.domain.chat.message.service.ChatMessageService;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    private final UserService userService;

    // 메시지 보내기
    @PostMapping
    public ResponseEntity<ChatMessage> sendMessage(
            @RequestParam Long chatRoomId,
            @RequestParam String content,
            Authentication authentication) {
        User sender = userService.getAuthenticatedUser(); // 현재 로그인된 사용자
        ChatMessage chatMessage = chatMessageService.sendMessage(chatRoomId, content, sender);
        return ResponseEntity.ok(chatMessage);
    }

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
}