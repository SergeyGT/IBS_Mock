package com.example.demo.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PaymentResponse(
        @JsonProperty("transaction_id") String transactionId,
        @JsonProperty("bank_bik") String bankBik,
        String status,
        List<PaymentContact> contact) {
}
