package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Kind {

    @Id
    @Column(nullable = false)
    private Long kind_no;   // 품종 코드

    @Column(length = 20, nullable = false)
    private String kind_nm; // 품종 명

    @Builder
    public Kind(Long kind_no, String kind_nm) {
        this.kind_no = kind_no;
        this.kind_nm = kind_nm;
    }
}
