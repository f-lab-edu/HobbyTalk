package com.jongho.user.dao.repository;

import com.jongho.user.dao.mapper.UserNotificationSettingMapper;
import com.jongho.user.domain.model.UserNotificationSetting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserNotificationSettingMybatisRepositoryImpl {
    UserNotificationSettingMapper userNotificationSettingMapper;

    public int createUserNotificationSetting(UserNotificationSetting userNotificationSetting) {
        return userNotificationSettingMapper.createUserNotificationSetting(userNotificationSetting);
    }
}
