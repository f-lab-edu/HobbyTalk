package com.jongho.user.application.service;

import com.jongho.user.domain.model.UserNotificationSetting;
import com.jongho.user.domain.repository.UserNotificationSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserNotificationSettingServiceImpl implements UserNotificationSettingService{
    private final UserNotificationSettingRepository userNotificationSettingRepository;
    @Override
    public int createUserNotificationSetting(int userId) {
        UserNotificationSetting userNotificationSetting = new UserNotificationSetting((long) userId, true, true, true);

        return userNotificationSettingRepository.createUserNotificationSetting(userNotificationSetting);
    }
}
