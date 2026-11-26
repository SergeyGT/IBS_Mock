package com.example.demo.api.controller;

import com.example.demo.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Transactions")
public class TransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @DeleteMapping(value = "/v1/transactions/cleare/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(summary = "Удалить транзакцию процессинга")
    public ResponseEntity<String> clearTransaction(@PathVariable String id) throws InterruptedException {
        LOGGER.info("DELETE /v1/transactions/cleare/{id}");
        transactionService.clearTransaction();
        return ResponseEntity.ok("deleted success");
    }
}
