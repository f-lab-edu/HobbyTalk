package com.jongho.user.application.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jongho.user.domain.model.User;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@ToString
public class UserSignUpDto {
    @NotNull
    @NotEmpty
    private final String nickname;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$", message = "비밀번호는 8자 이상의 영문, 숫자, 특수문자 조합이어야 합니다.")
    @NotNull
    @NotEmpty
    private final String password;

    @NotNull
    @NotEmpty
    @Email(message = "이메일 형식이 아닙니다.")
    private final String email;
    @NotNull
    @NotEmpty
    private final String phoneNumber;
    @Nullable
    private final String profileImage;

    @JsonCreator
    public UserSignUpDto(@JsonProperty("nickname") String nickname,
                         @JsonProperty("password") String password,
                         @JsonProperty("email") String email,
                         @JsonProperty("phone_number") String phoneNumber,
                         @JsonProperty("profile_image") String profileImage) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
    }

    public User toUser() {

        return new User(this.nickname, this.password, this.email, this.phoneNumber, this.profileImage);
    }

}
