package com.jongho.openChatRoom.application.dto.response;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class MyOpenChatRoomListDto {
    public final Long id;
    public final String title;
    public final String notice;
    public final int maximumCapacity;
    public final int currentAttendance;
    public final String createdTime;
    public final int isManager;
    public final String categoryName;
    public final String lastMessage;
}
