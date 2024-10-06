package com.gulbi.Backend.domain.chat.message.entity;

import com.gulbi.Backend.domain.chat.room.entity.Room;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@NoArgsConstructor
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @Column(nullable = false, length = 500)
    private String content;

    @Builder
    public Message(Room room, User sender, String content) {
        this.room = room;
        this.sender = sender;
        this.content = content;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
