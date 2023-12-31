package com.jongho.openChatRoom.presentation;

import com.google.gson.Gson;
import com.jongho.common.config.WebMvcConfig;
import com.jongho.common.interceptor.AuthInterceptor;
import com.jongho.openChatRoom.application.dto.request.OpenChatRoomCreateDto;
import com.jongho.openChatRoom.application.facade.OpenChatRoomFacade;
import com.jongho.openChatRoom.controller.OpenChatRoomController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        value = OpenChatRoomController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {AuthInterceptor.class, WebMvcConfig.class})
)
@DisplayName("OpenChatRoomController 클래스")
public class OpenChatRoomControllerTest {
    @MockBean
    private OpenChatRoomFacade openChatRoomFacade;
    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("createOpenChatRoom 메소드는")
    class Describe_createOpenChatRoom {
        private OpenChatRoomCreateDto openChatRoomCreateDto;

        @BeforeEach
        public void setUp() {
            openChatRoomCreateDto = new OpenChatRoomCreateDto(
                    "타이틀",
                    "공지사항",
                    1L,
                    200,
                    "비밀번호");
        }
        @Test
        @DisplayName("호출이 되면 status 201과 open chat room create라는 메세지를 반환한다.")
        void 호출이_되면_status_201과_open_chat_room_create라는_메세지를_반환한다() throws Exception{
            // given
            doNothing().when(openChatRoomFacade).createOpenChatRoomAndOpenChatRoomUser(1L, new OpenChatRoomCreateDto(
                    "타이틀",
                    "공지사항",
                    1L,
                    200,
                    "비밀번호"));
            Gson gson = new Gson();
            String openChatRoomCreateDtoJson = gson.toJson(openChatRoomCreateDto);

            // when
            mockMvc.perform(post("/api/v1/open-chat-rooms")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(openChatRoomCreateDtoJson))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.message").value("CREATED"))
                    .andDo(print());
        }
    }
}
