package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class kind {

    @Id
    @Column(nullable = false)
    private Long kind_no;

    @Column(length = 20, nullable = false)
    private String kind_nm;

    @Builder
    public kind(Long kind_no, String kind_nm) {
        this.kind_no = kind_no;
        this.kind_nm = kind_nm;
    }
}
