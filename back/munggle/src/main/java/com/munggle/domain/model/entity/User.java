package com.munggle.domain.model.entity;

import com.munggle.domain.model.entity.converter.PasswordConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    // 이메일 어노테이션 말고 라이브러리로 교체할 예정
    @Size(max = 50)
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String username;

    @Size(min = 8)
    @NotNull
    @NotBlank
    @Convert(converter = PasswordConverter.class)
    private String password;

    @Size(min = 2, max = 10)
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String nickname;

    @Size(max = 100)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Column(name = "is_enabled")
    private boolean isEnabled;

    public void changeNickname(String newNickname) {
        this.nickname = newNickname;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void writeDescription(String description) {
        this.description = description;
    }

    public void markAsEnabled() {
        this.isEnabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}