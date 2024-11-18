package com.gulbi.Backend.domain.chat.message.service;

import com.gulbi.Backend.domain.chat.message.dto.ChatMessageDto;
import com.gulbi.Backend.domain.chat.message.entity.ChatMessage;
import com.gulbi.Backend.domain.chat.room.entity.ChatRoom;
import com.gulbi.Backend.domain.chat.message.repository.ChatMessageRepository;
import com.gulbi.Backend.domain.chat.room.service.ChatRoomService;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;
    private final UserService userService;

    @Transactional
    public ChatMessage sendMessage(Long chatRoomId, String content, User sender) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be empty.");
        }
        ChatRoom chatRoom = chatRoomService.getChatRoomById(chatRoomId);
        ChatMessage message = new ChatMessage(chatRoom, sender, content);
        return chatMessageRepository.save(message);
    }

    public List<ChatMessageDto> getMessages(Long chatRoomId) {
        List<ChatMessage> messages = chatMessageRepository.findByChatRoomId(chatRoomId);

        // ChatMessage를 ChatMessageDTO로 변환
        return messages.stream()
                .map(message -> new ChatMessageDto(
                        message.getId(),
                        message.getContent(),
                        message.getSender().getId(),  // User ID
                        message.getChatRoom().getId(),  // ChatRoom ID
                        message.getTimestamp()
                ))
                .collect(Collectors.toList());

    }


    @Transactional
    public void markMessageAsRead(Long messageId) {
        ChatMessage message = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        message.markAsRead();
    }
}