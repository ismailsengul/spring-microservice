package com.ismailsengul.fraud;

import com.ismailsengul.clients.fraud.FraudCheckRequest;
import com.ismailsengul.clients.notification.NotificationClient;
import com.ismailsengul.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;
    private final NotificationClient notificationClient;

    public boolean isFraudulentCustomer(FraudCheckRequest fraudCheckRequest) {
        fraudCheckHistoryRepository.save(FraudCheckHistory.builder()
                .customerId(fraudCheckRequest.toCustomerId())
                .isFraudster(false)
                .createdAt(LocalDateTime.now())
                .build()
        );

        NotificationRequest notificationRequest = new NotificationRequest(
                fraudCheckRequest.toCustomerId(),
                fraudCheckRequest.toCustomerEmail(),
                fraudCheckRequest.sender(),
                "Fraud check for customer " + fraudCheckRequest.toCustomerId() + " is " + false
        );

        notificationClient.sendNotification(notificationRequest);
        return false;
    }
}