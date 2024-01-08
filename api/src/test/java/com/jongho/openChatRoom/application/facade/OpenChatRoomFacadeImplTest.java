package com.jongho.openChatRoom.application.facade;

import com.jongho.category.application.service.CategoryService;
import com.jongho.category.domain.model.Category;
import com.jongho.common.exception.AlreadyExistsException;
import com.jongho.common.exception.CategoryNotFoundException;
import com.jongho.common.exception.MaxChatRoomsExceededException;
import com.jongho.openChatRoom.application.dto.request.OpenChatRoomCreateDto;
import com.jongho.openChatRoom.application.service.OpenChatRoomService;
import com.jongho.openChatRoom.domain.model.OpenChatRoom;
import com.jongho.openChatRoomUser.application.service.OpenChatRoomUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("OpenChatRoomFacadeImpl 클래스")
public class OpenChatRoomFacadeImplTest {
    @Mock
    private OpenChatRoomService openChatRoomService;
    @Mock
    private OpenChatRoomUserService openChatRoomUserService;
    @Mock
    private CategoryService categoryService;
    @InjectMocks
    private OpenChatRoomFacadeImpl openChatRoomFacadeImpl;

    @Nested
    @DisplayName("createOpenChatRoom 메소드는")
    class Describe_createOpenChatRoom {
        private Long userId;
        private OpenChatRoomCreateDto openChatRoomCreateDto;
        private OpenChatRoom openChatRoom;
        @BeforeEach
        void setUp() {
            userId = 1L;
            openChatRoomCreateDto = new OpenChatRoomCreateDto(
                    "타이틀",
                    "공지사항",
                    1L,
                    200,
                    "비밀번호"
            );
            openChatRoom = new OpenChatRoom(
                    "타이틀",
                    "공지사항",
                    1L,
                    1L,
                    200,
                    "비밀번호"
            );
        }

        @Test
        @DisplayName("생성하려는 유저가 소유하고있는 챗룸이 5개 이상이면 MaxChatRoomsExceededException예외를 던진다")
        void 생성하려는_유저가_소유하고있는_챗룸이_5개_이상이면_MaxChatRoomsExceededException예외를_던진다() {
            // given
            when(openChatRoomService.getOpenChatRoomCountByManagerId(userId)).thenReturn(5);

            // when then
            assertThrows(MaxChatRoomsExceededException.class, () -> {openChatRoomFacadeImpl.createOpenChatRoomAndOpenChatRoomUser(userId, openChatRoomCreateDto);});
        }

        @Test
        @DisplayName("생성하려는 챗룸의 카테고리가 존재하지 않으면 CategoryNotFoundException예외를 던진다")
        void 생성하려는_챗룸의_카테고리가_존재하지_않으면_CategoryNotFoundException예외를_던진다() {
            // given
            when(openChatRoomService.getOpenChatRoomCountByManagerId(userId)).thenReturn(4);
            when(categoryService.getOneCategoryById(openChatRoomCreateDto.getCategoryId())).thenReturn(Optional.empty());

            // when then
            assertThrows(CategoryNotFoundException.class, () -> {openChatRoomFacadeImpl.createOpenChatRoomAndOpenChatRoomUser(userId, openChatRoomCreateDto);});
        }

        @Test
        @DisplayName("생성하려는 챗룸의 카테고리가 존재하고, 유저가 소유하고있는 챗룸이 5개 이하이면서, 이미 존재하는 챗룸이면 AlreadyExistsException예외를 던진다")
        void 생성하려는_챗룸의_카테고리가_존재하고_유저가_소유하고있는_챗룸이_5개_이하이면서_이미_존재하는_챗룸이면_AlreadyExistsException예외를_던진다() {
            // given
            when(openChatRoomService.getOpenChatRoomCountByManagerId(userId)).thenReturn(4);
            when(categoryService.getOneCategoryById(openChatRoomCreateDto.getCategoryId())).thenReturn(Optional.of(new Category("카테고리이름", 1L)));
            when(openChatRoomService.getOpenChatRoomByManagerIdAndTitle(userId, openChatRoomCreateDto.getTitle())).thenReturn(Optional.of(openChatRoom));

            // when then
            Exception e = assertThrows(AlreadyExistsException.class, () -> {openChatRoomFacadeImpl.createOpenChatRoomAndOpenChatRoomUser(userId, openChatRoomCreateDto);});
            assertEquals("이미 존재하는 채팅방입니다.", e.getMessage());
        }

        @Test
        @DisplayName("생성하려는 챗룸의 카테고리가 존재하고, 유저가 소유하고있는 챗룸이 5개 이하이면 챗룸을 생성하고, 챗룸유저를 생성한다")
        void 생성하려는_챗룸의_카테고리가_존재하고_유저가_소유하고있는_챗룸이_5개_이하이면_챗룸을_생성하고_챗룸유저를_생성한다() {
            // given
            when(openChatRoomService.getOpenChatRoomCountByManagerId(userId)).thenReturn(4);
            when(categoryService.getOneCategoryById(openChatRoomCreateDto.getCategoryId())).thenReturn(Optional.of(new Category("카테고리이름", 1L)));
            when(openChatRoomService.getOpenChatRoomByManagerIdAndTitle(userId, openChatRoomCreateDto.getTitle())).thenReturn(Optional.empty());
            doNothing().when(openChatRoomService).createOpenChatRoom(openChatRoom);


            // when
            openChatRoomFacadeImpl.createOpenChatRoomAndOpenChatRoomUser(userId, openChatRoomCreateDto);

            // then
            verify(openChatRoomService, times(1)).createOpenChatRoom(openChatRoom);
            verify(openChatRoomUserService, times(1)).createOpenChatRoomUser(any());
        }
    }

    @Nested
    @DisplayName("getMyOpenChatRoomList 메소드는")
    class Describe_getMyOpenChatRoomList {
        private Long userId;
        private int offset;
        @BeforeEach
        void setUp() {
            userId = 1L;
            offset = 0;
        }

        @Test
        @DisplayName("offset이 0이면 userId인자만 받는 getMyOpenChatRoomList를 호출한다")
        void offset이_0이면_userId인자만_받는_getMyOpenChatRoomList를_호출한다() {
            // given
            when(openChatRoomService.getMyOpenChatRoomList(userId)).thenReturn(null);

            // when
            openChatRoomFacadeImpl.getMyOpenChatRoomList(userId, offset);

            // then
            verify(openChatRoomService, times(1)).getMyOpenChatRoomList(userId);
        }

        @Test
        @DisplayName("offset이 0이 아니면 userId와 offset을 인자로 받는 getMyOpenChatRoomList를 호출한다")
        void offset이_0이_아니면_userId와_offset을_인자로_받는_getMyOpenChatRoomList를_호출한다() {
            // given
            offset = 1;
            when(openChatRoomService.getMyOpenChatRoomList(userId, offset)).thenReturn(null);

            // when
            openChatRoomFacadeImpl.getMyOpenChatRoomList(userId, offset);

            // then
            verify(openChatRoomService, times(1)).getMyOpenChatRoomList(userId, offset);
        }
    }
}
