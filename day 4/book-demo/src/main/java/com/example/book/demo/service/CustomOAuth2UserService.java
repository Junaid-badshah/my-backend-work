package com.example.book.demo.service;

import com.example.book.demo.entity.User;
import com.example.book.demo.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String picture = oAuth2User.getAttribute("picture");

        userRepository.findByEmail(email).ifPresentOrElse(
                existingUser -> {
                    existingUser.setName(name);
                    existingUser.setPicture(picture);
                    userRepository.save(existingUser);
                },
                () -> {
                    User newUser = new User(email, name, picture, "GOOGLE");
                    userRepository.save(newUser);
                }
        );

        return oAuth2User;
    }
}