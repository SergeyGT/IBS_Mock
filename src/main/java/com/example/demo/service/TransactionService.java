package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final long deleteDelaySeconds;

    public TransactionService(@Value("${processing.delete-delay-seconds:1}") long deleteDelaySeconds) {
        this.deleteDelaySeconds = deleteDelaySeconds;
    }

    public void clearTransaction() throws InterruptedException {
        Thread.sleep(deleteDelaySeconds * 1000L);
    }
}
