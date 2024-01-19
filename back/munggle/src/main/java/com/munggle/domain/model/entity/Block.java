package com.munggle.domain.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "blocks")
public class Block {

    @Id
    @Column(name = "block_id")
    private Long id;

    private Long blockFrom;

    private Long blockTo;

    private Boolean isClear;
}
