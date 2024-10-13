package com.ismailsengul.notification;

import com.ismailsengul.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest notificationRequest) {
        System.out.println("Sending notification: " + notificationRequest.message());

        Notification notification = Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .sender(notificationRequest.sender())
                .message(notificationRequest.message())
                .sendAt(LocalDateTime.now())
                .build();

        notificationRepository.saveAndFlush(notification);
    }
}
