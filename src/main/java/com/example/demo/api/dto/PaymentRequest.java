package com.example.demo.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record PaymentRequest(
        @JsonProperty("transaction_id") String transactionId,
        BigDecimal sum,
        @JsonProperty("need_processing") boolean needProcessing) {
}
