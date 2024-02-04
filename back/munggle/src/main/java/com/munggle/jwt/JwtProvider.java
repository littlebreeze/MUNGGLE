package com.munggle.jwt;

import com.munggle.domain.model.entity.User;
import com.munggle.domain.model.entity.type.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtProvider {

    private static final String AUTHORITIES_KEY = "authorities";
    private final String jwtHeaderKey;
    private final String secretKey;
    private final long refreshValidityInMilliseconds;
    private final long tokenValidityInMilliseconds;
    private Key key;

    public JwtProvider(
            @Value("${jwt.header}") String jwtHeaderKey,
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.refresh-token-validaity-in-seconds}") Long refreshValidityInMilliseconds,
            @Value("${jwt.token-validity-in-seconds}") Long tokenValidityInSeconds
    ) {
        this.jwtHeaderKey = jwtHeaderKey;
        this.secretKey = secretKey;
        this.refreshValidityInMilliseconds = refreshValidityInMilliseconds * 1000;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    }

    @PostConstruct
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(Authentication authentication) {
        return createToken(authentication, this.tokenValidityInMilliseconds);
    }

    public String createRefreshToken(Authentication authentication) {
        return createToken(authentication, this.refreshValidityInMilliseconds);
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(authority -> new SimpleGrantedAuthority("ROLE_" + authority))
                        .collect(Collectors.toCollection(ArrayList::new));
        Long id = Long.parseLong(claims.get("id").toString());
        String nickname = claims.get("nickname").toString();
        String authority = this.getAuthorities(token).get(0).getAuthority().substring(5);
        User principal = new User(id,
                "",
                "",
                nickname,
                null,
                null,
                null,
                Role.valueOf(authority),
                null,
                null,
                true,
                0);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.debug("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.debug("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.debug("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.debug("JWT 토큰이 비어있습니다.");
        }

        return false;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearToken = request.getHeader(jwtHeaderKey);
        if (bearToken != null && bearToken.startsWith("Bearer ")) {
            return bearToken.replace("Bearer", "");
        }

        return bearToken;
    }

    public String refreshAccessToken(String refreshToken) {
        if (validateToken(refreshToken)) {
            Authentication authentication = getAuthentication(refreshToken);
            return createAccessToken(authentication);
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }

    public List<GrantedAuthority> getAuthorities(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String[] authorities = claims.get("authorities", String.class)
                .split(",");

        if (!StringUtils.hasText(authorities[0])) {
            return Collections.emptyList();
        }

        return Arrays.stream(authorities)
                .map(authority -> new SimpleGrantedAuthority("ROLE_" + authority))
                .collect(Collectors.toList());
    }

    private String createToken(Authentication authentication, long tokenValidityInMilliseconds) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        User principal = (User) authentication.getPrincipal();
        long now = (new Date()).getTime();
        Date validity = new Date(now + tokenValidityInMilliseconds);

        return Jwts.builder()
                .claim(AUTHORITIES_KEY, authorities)
                .claim("id", principal.getId())
                .claim("nickname", principal.getNickname())
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(validity)
                .compact();
    }

}
