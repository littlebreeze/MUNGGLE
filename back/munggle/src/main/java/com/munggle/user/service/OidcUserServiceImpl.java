package com.munggle.user.service;

import com.munggle.domain.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OidcUserServiceImpl implements OidUserService{

    private final UserService userService;
    private String email;
    private DefaultOAuth2UserService defaultOAuth2UserService = new DefaultOAuth2UserService();

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = defaultOAuth2UserService.loadUser(userRequest);

        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            email = (String) oauth2User.getAttributes().get("email");
        }

        User user = (User) userService.loadUserByUsername(email);

        return user;
    }
}
