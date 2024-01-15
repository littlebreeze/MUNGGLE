package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    // 이메일 어노테이션 말고 라이브러리로 교체할 예정
    @Size(max = 50)
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String email;

    @Size(min = 8, max = 15)
    @NotNull
    @NotBlank
//    @Convert(converter = PasswordConverter.class)
    private String password;

    @Size(min = 2, max = 10)
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String nickname;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Column(name = "is_deleted")
    private boolean is_deleted;
}