package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class hospital {

    @Id
    @Column(nullable = false)
    private Long hospital_no;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String location;

    @Column(length = 20, nullable = false)
    private String run_time;

    @Column(length = 10, nullable = false)
    private String phone_number;

    public hospital(Long hospital_no, String name, String location, String run_time, String phone_number) {
        this.hospital_no = hospital_no;
        this.name = name;
        this.location = location;
        this.run_time = run_time;
        this.phone_number = phone_number;
    }
}
