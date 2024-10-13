package com.ismailsengul.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("fraud")
public interface FraudClient {

    @PostMapping(path = "api/v1/fraud-check")
    FraudCheckResponse isFraudster(@RequestBody FraudCheckRequest fraudCheckRequest);
}
