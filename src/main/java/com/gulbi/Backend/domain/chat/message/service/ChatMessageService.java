package com.gulbi.Backend.domain.chat.message.service;

import com.gulbi.Backend.domain.chat.message.entity.ChatMessage;
import com.gulbi.Backend.domain.chat.room.entity.ChatRoom;
import com.gulbi.Backend.domain.chat.message.repository.ChatMessageRepository;
import com.gulbi.Backend.domain.chat.room.service.ChatRoomService;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;
    private final UserService userService;

    // 메시지 저장
    public ChatMessage saveMessage(String senderEmail, Long chatRoomId, String content) {
        User sender = userService.findByEmail(senderEmail);
        ChatRoom chatRoom = chatRoomService.getChatRoomById(chatRoomId);

        ChatMessage chatMessage = new ChatMessage(sender, chatRoom, content);
        return chatMessageRepository.save(chatMessage);
    }

    // 특정 채팅방의 메시지 가져오기
    public List<ChatMessage> getMessagesByChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomService.getChatRoomById(chatRoomId);
        return chatMessageRepository.findByChatRoomOrderByTimestampAsc(chatRoom);
    }
}
