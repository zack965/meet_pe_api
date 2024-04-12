package com.meetpe_api.meetpe_api.Controllers;

import com.meetpe_api.meetpe_api.DTO.EntitiesDto.UserEntityDto;
import com.meetpe_api.meetpe_api.DTO.Responses.NotificationSetting.GetUserNotificationResponse;
import com.meetpe_api.meetpe_api.Entities.NotificationSetting;
import com.meetpe_api.meetpe_api.Entities.User;
import com.meetpe_api.meetpe_api.Services.NotificationSettingService;
import com.meetpe_api.meetpe_api.wrappers.NotificationSettingWrapper;
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
    private NotificationSettingWrapper notificationSettingWrapper;

    public NotificationSettingController(NotificationSettingService notificationSettingService, NotificationSettingWrapper notificationSettingWrapper) {
        this.notificationSettingService = notificationSettingService;
        this.notificationSettingWrapper = notificationSettingWrapper;
    }

    @GetMapping("/user_notification")
    @Operation(security = { @SecurityRequirement(name = "bearer-key"),@SecurityRequirement(name = "API-KEY") })
    public ResponseEntity<GetUserNotificationResponse> getUserNotification() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        Optional<NotificationSetting>  notificationSettingOptional = this.notificationSettingService.GetNotificationOfUser(currentUser);
        if(notificationSettingOptional.isPresent()){
            NotificationSetting notificationSetting = notificationSettingOptional.get();
            UserEntityDto userEntityDto = new UserEntityDto();
            userEntityDto.setEmail(currentUser.getEmail());
            userEntityDto.setFullname(currentUser.getFullName());

             GetUserNotificationResponse getUserNotificationResponse = this.notificationSettingWrapper.getUserNotification(userEntityDto,notificationSetting);



            return ResponseEntity.ok(getUserNotificationResponse);
        }
        return ResponseEntity.notFound().build();
    }
}
