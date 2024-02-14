package com.munggle.security;

import com.munggle.jwt.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //체인 완성본
//        http
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(
//                        sessionManagement -> sessionManagement
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeRequests(
//                        registry -> registry.requestMatchers(HttpMethod.POST, "/users").permitAll()
//                                .requestMatchers("/users/emails/verification-requests").permitAll()
//                                .requestMatchers("/users/emails/verifications").permitAll()
//                                .requestMatchers("/users/**").hasAnyRole("ADMIN", "MEMBER")
//                                .requestMatchers("/userpages/**").hasAnyRole("ADMIN", "MEMBER")
//                                .requestMatchers("/alarms/**").hasAnyRole("ADMIN", "MEMBER")
//                                .requestMatchers("/blocks/**").hasAnyRole("ADMIN", "MEMBER")
//                                .requestMatchers("/comments/**").hasAnyRole("ADMIN", "MEMBER")
//                                .requestMatchers("/dogs/**").hasAnyRole("ADMIN", "MEMBER")
//                                .requestMatchers("/dog-match/**").hasAnyRole("ADMIN", "MEMBER")
//                                .requestMatchers("/dogs/**").hasAnyRole("ADMIN", "MEMBER")
//                                .requestMatchers("/follows/**").hasAnyRole("ADMIN", "MEMBER")
//                                .requestMatchers("/posts/**").hasAnyRole("ADMIN", "MEMBER")
//                                .requestMatchers("/search/**").hasAnyRole("ADMIN", "MEMBER")
//                                .requestMatchers("/walks/**").hasAnyRole("ADMIN", "MEMBER")
//                                .requestMatchers("/message/**").hasAnyRole("ADMIN", "MEMBER")
//                                .anyRequest().authenticated()
//                )
//                .formLogin(
//                        configure -> configure.successHandler(new LoginAuthenticationSuccessHandler(jwtProvider))
//                                .failureHandler(new LoginAuthenticationFailureHandler()))
//                .exceptionHandling(
//                        configurer -> configurer.accessDeniedHandler(new JwtAccessDeniedHandler())
//                                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
//                )
//                .oauth2Login(
//                        configure -> configure.successHandler(new LoginAuthenticationSuccessHandler(jwtProvider))
//                                .failureHandler(new LoginAuthenticationFailureHandler())
//                )
//
//                .exceptionHandling(
//                        configurer -> configurer.accessDeniedHandler(new JwtAccessDeniedHandler())
//                                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
//                )
//                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider),
//                        UsernamePasswordAuthenticationFilter.class);

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(
                        registry -> registry.anyRequest().permitAll()
                )
                .formLogin(
                        configure -> configure.successHandler(new LoginAuthenticationSuccessHandler(jwtProvider))
                                .failureHandler(new LoginAuthenticationFailureHandler()))
                .exceptionHandling(
                        configurer -> configurer.accessDeniedHandler(new JwtAccessDeniedHandler())
                                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                )
                .oauth2Login(
                        configure -> configure.successHandler(new LoginAuthenticationSuccessHandler(jwtProvider))
                                .failureHandler(new LoginAuthenticationFailureHandler())
                )

                .exceptionHandling(
                        configurer -> configurer.accessDeniedHandler(new JwtAccessDeniedHandler())
                                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider),
                        UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of(
                "Authorization",
                "Cache-Control",
                "Content-Type",
                "X-AUTH-TOKEN"
        ));
        config.setExposedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}