package com.ismailsengul.fraud;

import com.example.clients.notification.NotificationClient;
import com.example.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;
    private final NotificationClient notificationClient;

    public boolean isFraudulentCustomer(Integer customerId) {
        fraudCheckHistoryRepository.save(FraudCheckHistory.builder()
                .customerId(customerId)
                .isFraudster(false)
                .createdAt(LocalDateTime.now())
                .build()
        );

        notificationClient.sendNotification(new NotificationRequest("Customer with id " + customerId + " is checked for fraud."));
        return false;
    }
}