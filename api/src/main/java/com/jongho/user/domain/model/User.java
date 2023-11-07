package com.jongho.user.domain.model;

import lombok.*;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private final String nickname;
    private final String password;
    private final String email;
    private final String phoneNumber;
    private final String profileImage;

}
