package com.example.demo.service;

import com.example.demo.api.dto.PaymentContact;
import com.example.demo.api.dto.PaymentRequest;
import com.example.demo.api.dto.PaymentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PaymentService {

    private static final DateTimeFormatter PROCESSING_TIME_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private static final List<String> TELECOM_VALUES =
            List.of("43t5h7", "k8fg534", "67hr333f", "gj6iire");

    public PaymentResult confirmPayment(String bankCode, PaymentRequest request) {
        if (!"22".equals(bankCode)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BankCode must be 22");
        }

        int telecomCount = bankCode.chars()
                .filter(Character::isDigit)
                .map(character -> character - '0')
                .sum();
        List<String> telecom = TELECOM_VALUES.subList(0, telecomCount);
        PaymentResponse response = new PaymentResponse(
                request.transactionId(),
                "2345678997",
                "accepted",
                List.of(new PaymentContact("HL pay company", telecom)));

        return new PaymentResult(response, LocalDateTime.now().format(PROCESSING_TIME_FORMAT));
    }

    public record PaymentResult(PaymentResponse response, String processingTime) {
    }
}
