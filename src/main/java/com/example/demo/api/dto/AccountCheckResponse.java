package com.example.demo.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AccountCheckResponse(
        String account,
        @JsonProperty("vip-client") boolean vipClient,
        boolean blocked,
        String inn,
        List<Debt> debt) {
}
