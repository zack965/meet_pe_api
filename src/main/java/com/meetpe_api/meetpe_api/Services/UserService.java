package com.meetpe_api.meetpe_api.Services;

import com.meetpe_api.meetpe_api.Entities.NotificationSetting;
import com.meetpe_api.meetpe_api.Entities.User;
import com.meetpe_api.meetpe_api.Repositories.NotificationSettingRepository;
import com.meetpe_api.meetpe_api.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final NotificationSettingRepository notificationSettingRepository;

    public UserService(UserRepository userRepository, NotificationSettingRepository notificationSettingRepository) {
        this.userRepository = userRepository;
        this.notificationSettingRepository = notificationSettingRepository;
    }

    public void InitAccount(User user){
        NotificationSetting notificationSetting = new NotificationSetting();
        notificationSetting.setUser(user);

        notificationSetting.setNotificationAppEmail(false);
        notificationSetting.setNotificationAppPush(false);
        notificationSetting.setNotificationAppSms(false);


        notificationSetting.setNotificationReservationEmail(false);
        notificationSetting.setNotificationReservationSms(false);
        notificationSetting.setNotificationReservationAppPush(false);
        this.notificationSettingRepository.save(notificationSetting);



    }

    public List<User> allUsers() {
//        List<User> users = new ArrayList<>();
//
//        userRepository.findAll().forEach(users::add);
//
//        return users;
        return (List<User>) userRepository.findAll();
    }
}
