package com.meetpe_api.meetpe_api.DTO.Responses.NotificationSetting;

import com.meetpe_api.meetpe_api.DTO.EntitiesDto.UserEntityDto;
import com.meetpe_api.meetpe_api.Entities.NotificationSetting;
import com.meetpe_api.meetpe_api.Entities.User;
import lombok.Data;

@Data
public class GetUserNotificationResponse {
    // private NotificationSetting notificationSetting;
    private boolean notificationAppEmail;
    private boolean notificationAppPush;
    private boolean notificationAppSms;

    private boolean notificationReservationEmail;
    private boolean notificationReservationAppPush;
    private boolean notificationReservationSms;
    private UserEntityDto user;
}
