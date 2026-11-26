package com.example.demo.service;

import com.example.demo.api.dto.AccountCheckResponse;
import com.example.demo.api.dto.Debt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    public AccountCheckResponse checkAccount(String account, int days) {
        if (account.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "acc must not be blank");
        }
        if (days < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "days must not be negative");
        }

        List<Debt> debts = new ArrayList<>(days);
        for (int i = 0; i < days; i++) {
            boolean parking = i % 2 == 0;
            debts.add(new Debt(parking ? 1000 : 3000, parking ? "parking" : "gkh"));
        }

        char lastCharacter = account.charAt(account.length() - 1);
        boolean vipClient = lastCharacter >= '0' && lastCharacter <= '9'
                && ((lastCharacter - '0') % 2 != 0);
        return new AccountCheckResponse(account, vipClient, false, account + "111", debts);
    }
}
