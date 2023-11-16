package com.jongho.user.presentation;

import com.google.gson.Gson;
import com.jongho.user.application.dto.request.UserSignUpDto;
import com.jongho.user.application.facade.UserFacade;
import com.jongho.user.application.service.UserService;
import com.jongho.user.presentation.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@DisplayName("UserController 클래스")
public class UserControllerTest {
    @MockBean
    private UserService userService;
    @MockBean
    private UserFacade userFacade;
    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("signUp 메소드는")
    class Describe_signUp {
        private UserSignUpDto userSignUpDto;

        @BeforeEach
        public void setUp() {
            userSignUpDto = new UserSignUpDto("jonghao", "a123b123!", "whdgh9595", "01012341234", "abc.jpg");
        }

        @Test
        @DisplayName("올바른 데이터를 받으면 UserFacade.userSignUpAndNotificationSettingCreate 을 호출하고 status 201과 user create라는 메세지를 반환한다.")
        void 올바른_데이터를_받으면_userFacade_userSignUpAndNotificationSettingCreate를_호출하고_status_201_및_user_create_메세지를_반환한다() throws Exception {
            // given
            doNothing().when(userFacade).userSignUpAndNotificationSettingCreate(userSignUpDto);
            Gson gson = new Gson();
            String userSignUpDtoJson = gson.toJson(userSignUpDto);

            // when
            mockMvc.perform(post("/api/v1/users/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(userSignUpDtoJson))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.message").value("user create"))
                    .andDo(print());

            // then
            verify(userFacade, times(1)).userSignUpAndNotificationSettingCreate(userSignUpDto);
        }
    }
}
