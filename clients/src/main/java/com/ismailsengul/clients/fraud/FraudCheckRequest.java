package com.ismailsengul.clients.fraud;


public record FraudCheckRequest(Integer toCustomerId,String toCustomerEmail,String sender) {
}
