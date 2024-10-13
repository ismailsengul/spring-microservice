package com.example.notification;

import com.example.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    public void sendNotification(NotificationRequest notificationRequest) {
        System.out.println("Sending notification: " + notificationRequest.message());
    }
}
