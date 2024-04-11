package com.meetpe_api.meetpe_api.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification_settings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean notificationAppEmail;
    private boolean notificationAppPush;
    private boolean notificationAppSms;

    private boolean notificationReservationEmail;
    private boolean notificationReservationAppPush;
    private boolean notificationReservationSms;

//    @OneToOne(mappedBy = "notificationSetting")
//    private User user;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "notification_id", referencedColumnName = "id")
//    private NotificationSetting notificationSetting;
    @OneToOne()
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


}
