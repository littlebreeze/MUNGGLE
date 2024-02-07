package com.munggle.domain.model.entity;

import com.munggle.domain.exception.IllegalNicknameException;
import com.munggle.domain.exception.IllegalPasswordException;
import com.munggle.domain.model.entity.converter.PasswordConverter;
import com.munggle.domain.model.entity.type.Role;
import com.munggle.util.NicknameValidator;
import com.munggle.util.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.munggle.domain.exception.ExceptionMessage.NICKNAME_ILLEGAL;
import static com.munggle.domain.exception.ExceptionMessage.PASSWORD_ILLEGAL;

@Entity
@Table(name = "users")
@DynamicUpdate
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
// 배경화면, 프로필 이미지 필드 필요
public class User implements UserDetails, OAuth2User, OidcUser {

    @Transient
    private OidcIdToken idToken;

    @Transient
    private OidcUserInfo userInfo;

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

    @JoinColumn(name = "background_image")
    @OneToOne
    private UserImage backgroundImage;

    @JoinColumn(name = "profile_image")
    @OneToOne
    private UserImage profileImage;

    @Size(max = 100)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime create_at;

    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @NotNull
    @Column(name = "is_enabled")
    private boolean isEnabled;

    private Integer followIncreaseCount = 0;

    @PrePersist
    public void prePersist() {
        if (!NicknameValidator.isValidNickname(this.nickname)) {
            throw new IllegalNicknameException(NICKNAME_ILLEGAL);
        }
        if (!PasswordValidator.isValidPassword(this.password)) {
            throw new IllegalPasswordException(PASSWORD_ILLEGAL);
        }
        this.lastModified = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastModified = LocalDateTime.now();
    }

    public void changeBackgroundImage(UserImage background) {
        this.backgroundImage = background;
    }

    public void changeProfileImage(UserImage profileImage) {
        this.profileImage = profileImage;
    }

    public void changeProfile(String nickname, String description) {
        if (!NicknameValidator.isValidNickname(nickname)) {
            throw new IllegalNicknameException(NICKNAME_ILLEGAL);
        }
        this.nickname = nickname;
        this.description = description;
    }

    public void changePassword(String newPassword) {
        if (!PasswordValidator.isValidPassword(newPassword)) {
            throw new IllegalPasswordException(PASSWORD_ILLEGAL);
        }
        this.password = newPassword;
    }

    public void markAsDeleted() {
        this.isEnabled = false;
    }

    public void resetFollowIncreaseCount() {
        this.followIncreaseCount = 0;
    }
    public void plusFollowIncreaseCount(){
        this.followIncreaseCount++;
    }
    public void minusFollowIncreaseCount(){
        this.followIncreaseCount--;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of("email", this.username);
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

    @Override
    public String getName() {
        return this.username;
    }

    @Override
    public Map<String, Object> getClaims() {
        return idToken != null ? idToken.getClaims() : Collections.emptyMap();
    }

    @Override
    public OidcIdToken getIdToken() {
        return idToken;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return userInfo;
    }
}