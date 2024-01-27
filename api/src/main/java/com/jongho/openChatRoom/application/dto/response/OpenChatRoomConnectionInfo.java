package com.jongho.openChatRoom.application.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OpenChatRoomConnectionInfo {
    private int active;
    private int unReadMessageCount;
    private int lastExitTime;

    @JsonCreator
    public OpenChatRoomConnectionInfo(
            @JsonProperty("active") int active,
            @JsonProperty("unReadMessageCount") int unReadMessageCount,
            @JsonProperty("lastExitTime") int lastExitTime) {
        this.active = active;
        this.unReadMessageCount = unReadMessageCount;
        this.lastExitTime = lastExitTime;
    }
}
