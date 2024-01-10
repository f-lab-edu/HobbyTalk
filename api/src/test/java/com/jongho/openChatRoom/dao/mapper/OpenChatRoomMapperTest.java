package com.jongho.openChatRoom.dao.mapper;

import com.jongho.common.dao.BaseMapperTest;
import com.jongho.openChatRoom.domain.model.OpenChatRoom;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
@DisplayName("OpenChatRoomMapper 인터페이스")
public class OpenChatRoomMapperTest extends BaseMapperTest {
    @Autowired
    private OpenChatRoomMapper openChatRoomMapper;

    @Nested
    @DisplayName("createOpenChatRoom 메소드는")
    class Describe_createOpenChatRoom {
        @BeforeEach
        void setUp() {
            cleanUpOpenChatRoomTable();
        }

        @Test
        @DisplayName("인자로 받은 오픈채팅방을 저장한다.")
        void 오픈채팅방을_생성한다() {
            // given
            OpenChatRoom openChatRoom = new OpenChatRoom(
                    "타이틀",
                    "공지사항",
                    1L,
                    1L,
                    200,
                    "비밀번호"
            );

            // when
            openChatRoomMapper.createOpenChatRoom(openChatRoom);

            // then
            assertEquals(1, openChatRoom.getId());
        }
    }

    @Nested
    @DisplayName("countByManagerId 메소드는")
    class Describe_countByManagerId {
        @BeforeEach
        void setUp() {
            cleanUpOpenChatRoomTable();
        }

        @Test
        @DisplayName("인자로 받은 매니저 아이디를 가진 오픈채팅방의 개수를 반환한다.")
        void 오픈채팅방의_개수를_반환한다() {
            // given
            OpenChatRoom openChatRoom = new OpenChatRoom(
                    "타이틀",
                    "공지사항",
                    1L,
                    1L,
                    200,
                    "비밀번호"
            );
            openChatRoomMapper.createOpenChatRoom(openChatRoom);

            // when
            int count = openChatRoomMapper.countByManagerId(openChatRoom.getManagerId());

            // then
            assertEquals(1, count);
        }
    }

    @Nested
    @DisplayName("updateIncrementCurrentCapacity 메소드는")
    class Describe_updateIncrementCurrentCapacity {
        @BeforeEach
        void setUp() {
            cleanUpOpenChatRoomTable();
        }

        @Test
        @DisplayName("인자로 받은 오픈채팅방의 현재 참여 인원을 1 증가시킨다.")
        void 오픈채팅방의_현재_참여_인원을_1_증가시킨다() {
            // given
            OpenChatRoom openChatRoom = new OpenChatRoom(
                    "타이틀",
                    "공지사항",
                    1L,
                    1L,
                    200,
                    "비밀번호"
            );
            openChatRoomMapper.createOpenChatRoom(openChatRoom);

            // when
            openChatRoomMapper.updateIncrementCurrentCapacity(openChatRoom.getId(), 1);

            // then
            assertEquals(2, openChatRoomMapper.selectOneOpenChatRoomByIdForUpdate(openChatRoom.getId()).getCurrentAttendance());
        }
    }

    @Nested
    @DisplayName("selectOneOpenChatRoomByManagerIdAndTitle 메소드는")
    class Describe_selectOneOpenChatRoomByManagerIdAndTitle {
        @BeforeEach
        void setUp() {
            cleanUpOpenChatRoomTable();
        }

        @Test
        @DisplayName("인자로 받은 매니저 아이디와 타이틀을 가진 오픈채팅방을 반환한다.")
        void 오픈채팅방을_반환한다() {
            // given
            OpenChatRoom openChatRoom = new OpenChatRoom(
                    "타이틀",
                    "공지사항",
                    1L,
                    1L,
                    200,
                    "비밀번호"
            );
            openChatRoomMapper.createOpenChatRoom(openChatRoom);

            // when
            OpenChatRoom result = openChatRoomMapper.selectOneOpenChatRoomByManagerIdAndTitle(openChatRoom.getManagerId(), openChatRoom.getTitle());

            // then
            assertEquals(openChatRoom.getId(), result.getId());
        }
    }

    @Nested
    @DisplayName("selectOneOpenChatRoomByIdForUpdate 메소드는")
    class Describe_selectOneOpenChatRoomByIdForUpdate {
        @BeforeEach
        void setUp() {
            cleanUpOpenChatRoomTable();
        }

        @Test
        @DisplayName("인자로 받은 오픈채팅방 아이디를 가진 오픈채팅방을 반환한다.")
        void 오픈채팅방을_반환한다() {
            // given
            OpenChatRoom openChatRoom = new OpenChatRoom(
                    "타이틀",
                    "공지사항",
                    1L,
                    1L,
                    200,
                    "비밀번호"
            );
            openChatRoomMapper.createOpenChatRoom(openChatRoom);

            // when
            OpenChatRoom result = openChatRoomMapper.selectOneOpenChatRoomByIdForUpdate(openChatRoom.getId());

            // then
            assertEquals(openChatRoom.getId(), result.getId());
        }
    }
}
