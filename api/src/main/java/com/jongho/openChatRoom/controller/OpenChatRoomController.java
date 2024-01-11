package com.jongho.openChatRoom.controller;

import com.jongho.common.annotaition.HttpRequestLogging;
import com.jongho.common.response.BaseMessageEnum;
import com.jongho.common.response.BaseResponseEntity;
import com.jongho.common.util.threadlocal.AuthenticatedUserThreadLocalManager;
import com.jongho.openChatRoom.application.dto.request.OpenChatRoomCreateDto;
import com.jongho.openChatRoom.application.dto.request.OpenChatRoomJoinDto;
import com.jongho.openChatRoom.application.dto.request.OpenChatRoomMembershipRequestDto;
import com.jongho.openChatRoom.application.facade.OpenChatRoomFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@HttpRequestLogging
@RequestMapping("/api/v1/open-chat-rooms")
public class OpenChatRoomController {
    private final OpenChatRoomFacade openChatRoomFacade;

    @PostMapping
    public ResponseEntity<BaseResponseEntity<?>> createOpenChatRoom(@Validated @RequestBody OpenChatRoomCreateDto openChatRoomCreateDto){
        openChatRoomFacade.createOpenChatRoomAndOpenChatRoomUser(AuthenticatedUserThreadLocalManager.get(), openChatRoomCreateDto);

        return BaseResponseEntity.create(BaseMessageEnum.CREATED.getValue());
    }

    @PostMapping("/{openChatRoomId}/join")
    public ResponseEntity<BaseResponseEntity<?>> joinOpenChatRoom(@PathVariable("openChatRoomId") Long openChatRoomId, @Validated @RequestBody OpenChatRoomJoinDto openChatRoomJoinDto){
        openChatRoomFacade.joinOpenChatRoom(AuthenticatedUserThreadLocalManager.get(), openChatRoomId, openChatRoomJoinDto.getPassword());

        return BaseResponseEntity.ok(BaseMessageEnum.SUCCESS.getValue());
    }

    @PostMapping("/{openChatRoomId}/membership-requests")
    public ResponseEntity<BaseResponseEntity<?>> createOpenChatRoomMembershipRequest(@PathVariable("openChatRoomId") Long openChatRoomId, @Validated @RequestBody OpenChatRoomMembershipRequestDto openChatRoomMembershipRequestDto){
        openChatRoomFacade.createOpenChatRoomMembershipRequest(AuthenticatedUserThreadLocalManager.get(), openChatRoomId, openChatRoomMembershipRequestDto.getMessage());

        return BaseResponseEntity.ok(BaseMessageEnum.SUCCESS.getValue());
    }
}
