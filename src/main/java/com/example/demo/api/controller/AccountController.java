package com.example.demo.api.controller;

import com.example.demo.api.dto.AccountCheckResponse;
import com.example.demo.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Accounts")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/v2/checkAccount")
    @Operation(summary = "Проверить задолженности по карте клиента")
    public ResponseEntity<AccountCheckResponse> checkAccount(
            @Parameter(description = "Номер счета") @RequestParam String acc,
            @Parameter(description = "Количество возвращаемых задолженностей")
            @RequestParam int days) {
        LOGGER.info("GET /v2/checkAccount");
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(accountService.checkAccount(acc, days));
    }
}
