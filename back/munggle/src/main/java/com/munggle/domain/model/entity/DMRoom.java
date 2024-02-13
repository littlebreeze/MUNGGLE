package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dm_rooms")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DMRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dm_room_id")
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "dm_group_users",
            joinColumns = @JoinColumn(name = "dm_room_id"), // DMRoom 엔티티의 PK 컬럼과 매핑
            inverseJoinColumns = @JoinColumn(name = "id") // User 엔티티의 PK 컬럼과 매핑
    )
    private Set<User> users;

    @OneToMany(mappedBy = "dmId")
    private List<ChatMessage> chatMessages;
}
