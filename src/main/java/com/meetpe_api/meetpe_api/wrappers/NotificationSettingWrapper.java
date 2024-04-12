package com.meetpe_api.meetpe_api.wrappers;

import com.meetpe_api.meetpe_api.DTO.EntitiesDto.UserEntityDto;
import com.meetpe_api.meetpe_api.DTO.Responses.NotificationSetting.GetUserNotificationResponse;
import com.meetpe_api.meetpe_api.Entities.NotificationSetting;
import org.springframework.stereotype.Component;

@Component
public class NotificationSettingWrapper {
    public GetUserNotificationResponse getUserNotification(UserEntityDto userEntityDto,NotificationSetting notificationSetting) {
        GetUserNotificationResponse response = new GetUserNotificationResponse();
        response.setUser(userEntityDto);

        response.setNotificationAppPush(notificationSetting.isNotificationAppPush());
        response.setNotificationAppSms(notificationSetting.isNotificationAppSms());
        response.setNotificationAppEmail(notificationSetting.isNotificationAppEmail());


        response.setNotificationReservationEmail(notificationSetting.isNotificationReservationEmail());
        response.setNotificationReservationSms(notificationSetting.isNotificationReservationSms());
        response.setNotificationReservationAppPush(notificationSetting.isNotificationReservationAppPush());

        return response;
    }
}
