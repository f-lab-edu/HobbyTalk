package com.jongho.openChatRoom.application.service;

import com.jongho.openChatRoom.domain.model.OpenChatRoom;
import com.jongho.openChatRoom.domain.repository.OpenChatRoomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("OpenChatRoomServiceImpl 클래스")
public class OpenChatRoomServiceImplTest {
    @Mock
    private OpenChatRoomRepository openChatRoomRepository;
    @InjectMocks
    private OpenChatRoomServiceImpl openChatRoomServiceImpl;

    @Nested
    @DisplayName("createOpenChatRoom 메소드는")
    class Describe_createOpenChatRoom {
        @Test
        @DisplayName("OpenChatRoomRepository의 createOpenChatRoom 메소드를 호출한다")
        void OpenChatRoomRepositoryl의_createOpenChatRoom메소드를_한번_호출한다() {
            // given
            OpenChatRoom openChatRoom = new OpenChatRoom(
                    "타이틀",
                    "공지사항",
                    1L,
                    1L,
                    200,
                    "비밀번호"
            );
            doNothing().when(openChatRoomRepository).createOpenChatRoom(openChatRoom);

            // when
            openChatRoomServiceImpl.createOpenChatRoom(openChatRoom);

            // then
            verify(openChatRoomRepository, times(1)).createOpenChatRoom(openChatRoom);
        }
    }

    @Nested
    @DisplayName("countByManagerId 메소드는")
    class Describe_countByManagerId {
        @Test
        @DisplayName("OpenChatRoomRepository의 countByManagerId 메소드를 호출해서 받은 count를 반환한다")
        void OpenChatRoomRepository의_countByManagerId메소드를_한번_호출해서_받은_count를_반환한다() {
            // given
            Long managerId = 1L;
            when(openChatRoomRepository.countByManagerId(managerId)).thenReturn(5);

            // when
            int result = openChatRoomServiceImpl.getOpenChatRoomCountByManagerId(managerId);

            // then
            verify(openChatRoomRepository, times(1)).countByManagerId(managerId);
            assertEquals(5, result);
        }
    }

    @Nested
    @DisplayName("getMyOpenChatRoomList 메소드는")
    class Describe_getMyOpenChatRoomList {
        @Test
        @DisplayName("OpenChatRoomRepository의 selectMyOpenChatRoomListByUserId 메소드를 호출한다")
        void OpenChatRoomRepository의_selectMyOpenChatRoomListByUserId메소드를_한번_호출한다() {
            // given
            Long userId = 1L;

            // when
            openChatRoomServiceImpl.getMyOpenChatRoomList(userId);

            // then
            verify(openChatRoomRepository, times(1)).selectMyOpenChatRoomListByUserId(userId);
        }
    }

    @Nested
    @DisplayName("getMyOpenChatRoomList 메소드는")
    class Describe_getMyOpenChatRoomListWithOffset {
        @Test
        @DisplayName("OpenChatRoomRepository의 selectMyOpenChatRoomListByUserIdAndOffset 메소드를 호출한다")
        void OpenChatRoomRepository의_selectMyOpenChatRoomListByUserIdAndOffset메소드를_한번_호출한다() {
            // given
            Long userId = 1L;
            int offset = 1;

            // when
            openChatRoomServiceImpl.getMyOpenChatRoomList(userId, offset);

            // then
            verify(openChatRoomRepository, times(1)).selectMyOpenChatRoomListByUserIdAndOffset(userId, offset);
        }
    }
}
