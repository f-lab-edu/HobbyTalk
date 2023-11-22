package com.jongho.user.application.facade;

import com.jongho.user.application.dto.request.UserSignUpDto;
import com.jongho.user.application.service.UserNotificationSettingService;
import com.jongho.user.application.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserFacade 클래스")
public class UserFacadeImplTest {
    @Mock
    private UserService userService;
    @Mock
    private UserNotificationSettingService userNotificationSettingService;
    @InjectMocks
    private UserFacadeImpl userFacadeImpl;

    @Nested
    @DisplayName("userSignUpAndNotificationSettingCreate 메소드는")
    class Describe_userSignUpAndNotificationSettingCreate {
        @Test
        @DisplayName("UserService.signUp과 UserNotificationSettingService.createUserNotificationSetting을 호출하고 완료한다.")
        void 유저_회원가입과_알림_설정을_생성한다() {
            // given
            UserSignUpDto userSignUpDto = new UserSignUpDto("jonghao", "a123b123", "whdgh9595", "01012341234", null);
            when(userService.signUp(userSignUpDto)).thenReturn(1);
            when(userNotificationSettingService.createUserNotificationSetting(1)).thenReturn(1);

            // when
            userFacadeImpl.signUpUserAndCreateNotificationSetting(userSignUpDto);

            // then
             verify(userService, times(1)).signUp(userSignUpDto);
             verify(userNotificationSettingService, times(1)).createUserNotificationSetting(1);
        }
    }
}
