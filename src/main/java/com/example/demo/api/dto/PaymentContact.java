package com.example.demo.api.dto;

import java.util.List;

public record PaymentContact(String name, List<String> telecom) {
}
