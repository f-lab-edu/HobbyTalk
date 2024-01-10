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

import java.util.Optional;

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
        void OpenChatRoomRepository의_createOpenChatRoom메소드를_한번_호출한다() {
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
    @DisplayName("updateIncrementCurrentCapacity 메소드는")
    class Describe_updateIncrementCurrentCapacity {
        @Test
        @DisplayName("OpenChatRoomRepository의 updateIncrementCurrentCapacity 메소드를 호출한다")
        void OpenChatRoomRepository의_updateIncrementCurrentCapacity메소드를_한번_호출한다() {
            // given
            Long openChatRoomId = 1L;
            int currentAttendance = 1;
            doNothing().when(openChatRoomRepository).updateIncrementCurrentCapacity(openChatRoomId, currentAttendance);

            // when
            openChatRoomServiceImpl.incrementOpenChatRoomCurrentAttendance(openChatRoomId, currentAttendance);

            // then
            verify(openChatRoomRepository, times(1)).updateIncrementCurrentCapacity(openChatRoomId, currentAttendance);
        }
    }

    @Nested
    @DisplayName("selectOneOpenChatRoomByManagerIdAndTitle 메소드는")
    class Describe_selectOneOpenChatRoomByManagerIdAndTitle {
        @Test
        @DisplayName("OpenChatRoomRepository의 selectOneOpenChatRoomByManagerIdAndTitle 메소드를 호출해서 받은 openChatRoom을 반환한다")
        void OpenChatRoomRepository의_selectOneOpenChatRoomByManagerIdAndTitle메소드를_한번_호출해서_받은_openChatRoom을_반환한다() {
            // given
            Long managerId = 1L;
            String title = "타이틀";
            OpenChatRoom openChatRoom = new OpenChatRoom(
                    "타이틀",
                    "공지사항",
                    1L,
                    1L,
                    200,
                    "비밀번호"
            );
            when(openChatRoomRepository.selectOneOpenChatRoomByManagerIdAndTitle(managerId, title)).thenReturn(Optional.of(openChatRoom));

            // when
            OpenChatRoom result = openChatRoomServiceImpl.getOpenChatRoomByManagerIdAndTitle(managerId, title).get();

            // then
            verify(openChatRoomRepository, times(1)).selectOneOpenChatRoomByManagerIdAndTitle(managerId, title);
            assertEquals(openChatRoom, result);
        }
    }

    @Nested
    @DisplayName("selectOneOpenChatRoomById 메소드는")
    class Describe_selectOneOpenChatRoomById {
        @Test
        @DisplayName("OpenChatRoomRepository의 selectOneOpenChatRoomById 메소드를 호출해서 받은 openChatRoom을 반환한다")
        void OpenChatRoomRepository의_selectOneOpenChatRoomById메소드를_한번_호출해서_받은_openChatRoom을_반환한다() {
            // given
            Long openChatRoomId = 1L;
            OpenChatRoom openChatRoom = new OpenChatRoom(
                    "타이틀",
                    "공지사항",
                    1L,
                    1L,
                    200,
                    "비밀번호"
            );
            when(openChatRoomRepository.selectOneOpenChatRoomById(openChatRoomId)).thenReturn(Optional.of(openChatRoom));

            // when
            OpenChatRoom result = openChatRoomServiceImpl.getOpenChatRoomById(openChatRoomId).get();

            // then
            verify(openChatRoomRepository, times(1)).selectOneOpenChatRoomById(openChatRoomId);
            assertEquals(openChatRoom, result);
        }
    }
}
