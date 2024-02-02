package com.munggle.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtRefreshTokenFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String requestUrl = request.getRequestURI();
        if (!requestUrl.contains("/refresh")) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println(request.getHeader("Refresh-Token"));
        String refreshToken = jwtProvider.resolveRefreshToken(request);

        if (refreshToken == null || !jwtProvider.validateToken(refreshToken)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Refresh Token is invalid");
            return;
        }

        Authentication authentication = jwtProvider.getAuthentication(refreshToken);
        String newAccessToken = jwtProvider.createAccessToken(authentication);
        response.setHeader("Authorization", "Bearer " + newAccessToken);
    }
}
