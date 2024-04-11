package com.meetpe_api.meetpe_api.Controllers;

import com.meetpe_api.meetpe_api.DTO.EntitiesDto.UserEntityDto;
import com.meetpe_api.meetpe_api.DTO.Responses.NotificationSetting.GetUserNotificationResponse;
import com.meetpe_api.meetpe_api.Entities.NotificationSetting;
import com.meetpe_api.meetpe_api.Entities.User;
import com.meetpe_api.meetpe_api.Services.NotificationSettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/app")
public class NotificationSettingController {
    private NotificationSettingService notificationSettingService;
    public NotificationSettingController(NotificationSettingService notificationSettingService) {
        this.notificationSettingService = notificationSettingService;
    }
    @GetMapping("/user_notification")
    @Operation(security = { @SecurityRequirement(name = "bearer-key"),@SecurityRequirement(name = "API-KEY") })
    public ResponseEntity<GetUserNotificationResponse> getUserNotification() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        Optional<NotificationSetting>  notificationSetting = this.notificationSettingService.GetNotificationOfUser(currentUser);
        if(notificationSetting.isPresent()){
            UserEntityDto userEntityDto = new UserEntityDto();
            GetUserNotificationResponse getUserNotificationResponse = new GetUserNotificationResponse();
            userEntityDto.setEmail(currentUser.getEmail());
            userEntityDto.setFullname(currentUser.getFullName());
            getUserNotificationResponse.setUser(userEntityDto);

            getUserNotificationResponse.setNotificationAppPush(notificationSetting.get().isNotificationAppPush());
            getUserNotificationResponse.setNotificationAppSms(notificationSetting.get().isNotificationAppSms());
            getUserNotificationResponse.setNotificationAppEmail(notificationSetting.get().isNotificationAppEmail());


            getUserNotificationResponse.setNotificationReservationEmail(notificationSetting.get().isNotificationReservationEmail());
            getUserNotificationResponse.setNotificationReservationSms(notificationSetting.get().isNotificationReservationSms());
            getUserNotificationResponse.setNotificationReservationAppPush(notificationSetting.get().isNotificationReservationAppPush());


            return ResponseEntity.ok(getUserNotificationResponse);
        }
        return ResponseEntity.notFound().build();
       // return notificationSetting.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
