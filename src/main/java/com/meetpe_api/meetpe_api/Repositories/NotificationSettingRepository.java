package com.meetpe_api.meetpe_api.Repositories;

import com.meetpe_api.meetpe_api.Entities.NotificationSetting;
import com.meetpe_api.meetpe_api.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationSettingRepository extends JpaRepository<NotificationSetting,Integer> {


    Optional<NotificationSetting> findByUser(User user);
}
