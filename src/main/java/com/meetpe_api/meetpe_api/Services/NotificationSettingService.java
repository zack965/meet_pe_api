package com.meetpe_api.meetpe_api.Services;

import com.meetpe_api.meetpe_api.Entities.NotificationSetting;
import com.meetpe_api.meetpe_api.Entities.User;
import com.meetpe_api.meetpe_api.Repositories.NotificationSettingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationSettingService {
    private NotificationSettingRepository notificationSettingRepository;
    public NotificationSettingService(NotificationSettingRepository notificationSettingRepository) {
        this.notificationSettingRepository = notificationSettingRepository;
    }
    public Optional<NotificationSetting> GetNotificationOfUser(User user){
        return this.notificationSettingRepository.findByUser(user);
    }
}
