package com.meetpe_api.meetpe_api.wrappers;

import com.meetpe_api.meetpe_api.DTO.EntitiesDto.UserEntityDto;
import com.meetpe_api.meetpe_api.DTO.Responses.NotificationSetting.GetUserNotificationResponse;
import com.meetpe_api.meetpe_api.Entities.NotificationSetting;
import com.meetpe_api.meetpe_api.Entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationSettingWrapperTest {

    private NotificationSettingWrapper notificationSettingWrapper;
    @BeforeEach
    void setUp() {
        notificationSettingWrapper = new NotificationSettingWrapper();
    }
    @Test
    public void shouldMapNotificationSettingToGetUserNotificationResponse(){
        UserEntityDto userEntityDto = new UserEntityDto();
        NotificationSetting notificationSetting = new NotificationSetting();
        User user = new User();
        user.setEmail("email user");
        userEntityDto.setFullname("full name");
        userEntityDto.setEmail("full email");
        notificationSetting.setUser(user);
        notificationSetting.setNotificationAppPush(true);
        notificationSetting.setNotificationAppSms(false);
        notificationSetting.setNotificationAppEmail(true);
        notificationSetting.setNotificationReservationEmail(false);
        notificationSetting.setNotificationReservationSms(true);
        notificationSetting.setNotificationReservationAppPush(true);
        System.out.println(notificationSetting.getUser().getEmail());
        GetUserNotificationResponse response  = notificationSettingWrapper.getUserNotification(userEntityDto,notificationSetting);
        assertEquals(userEntityDto, response.getUser());
        assertEquals(userEntityDto.getFullname(), response.getUser().getFullname());

    }
}