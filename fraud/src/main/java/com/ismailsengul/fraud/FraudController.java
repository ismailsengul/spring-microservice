package com.ismailsengul.fraud;

import com.ismailsengul.clients.fraud.FraudCheckRequest;
import com.ismailsengul.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {
    private final FraudCheckService fraudCheckService;

    @PostMapping
    FraudCheckResponse isFraudster(@RequestBody FraudCheckRequest fraudCheckRequest) {
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(fraudCheckRequest);
        log.info("Fraud check for customer {} is {}", fraudCheckRequest.toCustomerId(), isFraudulentCustomer);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}