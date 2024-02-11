package com.jongho.openChat.application.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PaginationDto {
    private final int offset;
    private final int limit;
    private final String lastCreatedTime;

    @JsonCreator
    public PaginationDto(
            @JsonProperty("offset") int offset,
            @JsonProperty("limit") int limit,
            @JsonProperty("last_created_time") String lastCreatedTime) {
        this.offset = offset;
        this.limit = limit;
        this.lastCreatedTime = lastCreatedTime;
    }
}
