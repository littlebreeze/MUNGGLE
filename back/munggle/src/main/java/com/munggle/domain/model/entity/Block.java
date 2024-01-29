package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blocks")
public class Block {

    @EmbeddedId
    private BlockId id;

    @ManyToOne
    @MapsId("blockFromId")
    @JoinColumn(name = "blocK_from_id")
    private User blockFrom;

    @ManyToOne
    @MapsId("blockToId")
    @JoinColumn(name = "block_to_id")
    private User blockTo;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    public void block() {
        this.isBlocked = true;
    }

    public void unBlock() {
        this.isBlocked = false;
    }
}
