package com.jongho.user.domain.model.redis;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public
class CachedUserProfile{
    private final Long id;
    private final String nickname;
    private final String profileImage;

    @JsonCreator
    public CachedUserProfile(
            @JsonProperty("id") Long id,
            @JsonProperty("nickname") String name,
            @JsonProperty("profileImage") String profileImage) {
        this.id = id;
        this.nickname = name;
        this.profileImage = profileImage;
    }
}
