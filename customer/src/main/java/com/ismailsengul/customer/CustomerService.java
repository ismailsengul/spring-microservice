package com.ismailsengul.customer;

import com.ismailsengul.clients.fraud.FraudCheckRequest;
import com.ismailsengul.clients.fraud.FraudCheckResponse;
import com.ismailsengul.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient
                .isFraudster(new FraudCheckRequest(
                        customer.getId(),
                        customer.getEmail(),
                        customer.getFirstName()
                ));

        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

    }
}