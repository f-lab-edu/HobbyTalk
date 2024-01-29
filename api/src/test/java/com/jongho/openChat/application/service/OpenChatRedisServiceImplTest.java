package com.jongho.openChat.application.service;

import com.jongho.openChat.domain.model.OpenChat;
import com.jongho.openChat.domain.repository.OpenChatRedisRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("OpenChatRedisServiceImpl 클래스")
public class OpenChatRedisServiceImplTest {
    @Mock
    private OpenChatRedisRepository openChatRedisRepository;
    @InjectMocks
    private OpenChatRedisServiceImpl openChatRedisService;
    @Nested
    @DisplayName("getLastOpenChatByOpenChatRoomId 메소드는")
    class Describe_getLastOpenChatByOpenChatRoomId {
        @Test
        @DisplayName("openChatRedisRepository.selectLastOpenChatByChatRoomId 메소드를 호출하고 받은 반환값을 반환한다.")
        void openChatRedisRepository_selectLastOpenChatByChatRoomId_메소드를_호출하고_받은_반환값을_반환한다() {
            // given
            Optional<OpenChat> openChat = Optional.of(new OpenChat(
                    1L,
                    1L,
                    1L,
                    "test",
                    1,
                    0,
                    null,
                    "2024-01-29 00:00:00"
            ));
            when(openChatRedisRepository.selectLastOpenChatByChatRoomId(1L)).thenReturn(openChat);

            // when
            Optional<OpenChat> result = openChatRedisService.getLastOpenChatByOpenChatRoomId(1L);

            // then
            verify(openChatRedisRepository).selectLastOpenChatByChatRoomId(1L);
            assertEquals(openChat, result);
        }
        @Nested
        @DisplayName("getOpenChatListByOpenChatRoomId 메소드는")
        class Describe_getOpenChatListByOpenChatRoomId {
            @Test
            @DisplayName("openChatRedisRepository.selectOpenChatListByChatRoomId 메소드를 호출하고 받은 반환값을 반환한다.")
            void openChatRedisRepository_selectOpenChatListByChatRoomId_메소드를_호출하고_받은_반환값을_반환한다() {
                // given
                List<OpenChat> openChatList = List.of(new OpenChat(
                        1L,
                        1L,
                        1L,
                        "test",
                        1,
                        0,
                        null,
                        "2024-01-29 00:00:00"
                ));
                when(openChatRedisRepository.selectOpenChatListByChatRoomId(1L)).thenReturn(openChatList);

                // when
                List<OpenChat> result = openChatRedisService.getOpenChatListByOpenChatRoomId(1L);

                // then
                verify(openChatRedisRepository).selectOpenChatListByChatRoomId(1L);
                assertEquals(1, result.size());
            }
        }
    }
}
