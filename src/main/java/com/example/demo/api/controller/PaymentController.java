package com.example.demo.api.controller;

import com.example.demo.api.dto.PaymentRequest;
import com.example.demo.api.dto.PaymentResponse;
import com.example.demo.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Payments")
public class PaymentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/v2/payment/")
    @Operation(summary = "Подтвердить платеж")
    public ResponseEntity<PaymentResponse> confirmPayment(
            @RequestHeader("BankCode") String bankCode,
            @RequestBody PaymentRequest request) {
        LOGGER.info("POST /v2/payment/");
        PaymentService.PaymentResult result = paymentService.confirmPayment(bankCode, request);
        return ResponseEntity.ok()
                .header("ProcessingTIme", result.processingTime())
                .body(result.response());
    }
}
