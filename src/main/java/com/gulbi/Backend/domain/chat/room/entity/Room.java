package com.gulbi.Backend.domain.chat.room.entity;

import com.gulbi.Backend.domain.chat.message.entity.Message;
import com.gulbi.Backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 첫 번째 참여자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant1_id", nullable = false)
    private User participant1;

    // 두 번째 참여자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant2_id", nullable = false)
    private User participant2;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    @Builder
    public Room(User participant1, User participant2) {
        this.participant1 = participant1;
        this.participant2 = participant2;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
        message.setRoom(this);
    }
}
