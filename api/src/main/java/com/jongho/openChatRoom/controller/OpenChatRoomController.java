package com.jongho.openChatRoom.controller;

import com.jongho.common.annotaition.HttpRequestLogging;
import com.jongho.common.response.BaseMessageEnum;
import com.jongho.common.response.BaseResponseEntity;
import com.jongho.common.util.threadlocal.AuthenticatedUserThreadLocalManager;
import com.jongho.openChatRoom.application.dto.request.OpenChatRoomCreateDto;
import com.jongho.openChatRoom.application.dto.response.MyOpenChatRoomListDto;
import com.jongho.openChatRoom.application.facade.OpenChatRoomFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/my")
    public ResponseEntity<BaseResponseEntity<List<MyOpenChatRoomListDto>>> getMyOpenChatRoomList(@RequestParam(value = "offset", defaultValue = "0") int offset){
        return BaseResponseEntity.ok(
                openChatRoomFacade.getMyOpenChatRoomList(AuthenticatedUserThreadLocalManager.get(), offset),
                BaseMessageEnum.SUCCESS.getValue());
    }
}
