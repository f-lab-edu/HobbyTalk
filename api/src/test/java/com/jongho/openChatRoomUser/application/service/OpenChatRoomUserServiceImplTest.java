package com.jongho.openChatRoomUser.application.service;

import com.jongho.openChatRoomUser.domain.model.OpenChatRoomUser;
import com.jongho.openChatRoomUser.domain.repository.OpenChatRoomUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("OpenChatRoomUserServiceImpl 클래스")
public class OpenChatRoomUserServiceImplTest {
    @Mock
    private OpenChatRoomUserRepository openChatRoomUserRepository;
    @InjectMocks
    private OpenChatRoomUserServiceImpl openChatRoomUserServiceImpl;

    @Nested
    @DisplayName("createOpenChatRoomUser 메소드는")
    class Describe_createOpenChatRoomUser {
        @Test
        @DisplayName("OpenChatRoomUserServiceImpl의 createOpenChatRoomUser 메소드를 호출한다")
        void OpenChatRoomUserServiceImpl의_createOpenChatRoomUser메소드를_한번_호출한다() {
            // given
            OpenChatRoomUser openChatRoomUser = new OpenChatRoomUser(
                    1L,
                    2L
            );
            doNothing().when(openChatRoomUserRepository).createOpenChatRoomUser(openChatRoomUser);

            // when
            openChatRoomUserServiceImpl.createOpenChatRoomUser(openChatRoomUser);

            // then
            verify(openChatRoomUserRepository, times(1)).createOpenChatRoomUser(openChatRoomUser);
        }
    }
}
