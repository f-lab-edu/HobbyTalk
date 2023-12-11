package com.jongho.user.application.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class UserSignInDto {
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$", message = "비밀번호는 8자 이상의 영문, 숫자, 특수문자 조합이어야 합니다.")
    @NotNull
    @NotEmpty
    private final String password;

    @NotNull
    @NotEmpty
    private final String username;

    @JsonCreator
    public UserSignInDto(@JsonProperty("password") String password,
                         @JsonProperty("username") String username) {
        this.password = password;
        this.username = username;
    }
}
