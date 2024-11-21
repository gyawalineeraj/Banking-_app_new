package com.ng.NgBank.controller;

import com.ng.NgBank.entity.TransactionHistory;
import com.ng.NgBank.exception.AccountUnverified;
import com.ng.NgBank.service.AccountService;
import com.ng.NgBank.service.TransactionHistoryService;
import com.ng.NgBank.util.TransactionType;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
@Tag(name = "AccountServies")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(@RequestHeader String accountNo) throws AccountUnverified {
        var account = accountService.getAccount(accountNo);
        String balance = accountService.getBalance(account);
        Map<String ,String> maps = new HashMap<>();
        maps.put("balance",balance);
        return ResponseEntity.ok().body(maps);
    }

    @PutMapping("/deposit")
    public ResponseEntity<?> depositMoney(@RequestHeader String accountNo,
                                          @RequestHeader double amount) throws AccountUnverified {
        var account = accountService.getAccount(accountNo);
        String newBalance = accountService.depositMoney(account,amount);

        Map<String ,String> maps = new HashMap<>();
        maps.put("Total-New-Balance",newBalance);

        return ResponseEntity.ok().body(maps);
    }

    @PutMapping("/withdraw")
    public ResponseEntity<?> withdrawMoney(@RequestHeader String accountNo,
                                          @RequestHeader double amount) throws AccountUnverified {

        var account = accountService.getAccount(accountNo);
        String newBalance = accountService.withdrawMoney(account,amount);
        Map<String ,String> maps = new HashMap<>();
        maps.put("Total-New-Balance",newBalance);

        return ResponseEntity.ok().body(maps);
    }
    @PutMapping("/withdraw-test")
    public ResponseEntity<?> withdrawMoneytest(@RequestHeader String accountNo,
                                          @RequestHeader double amount) throws AccountUnverified {
        var account = accountService.getAccount(accountNo);
        String newBalance = accountService.withdrawMoney(account,amount);
        Map<String ,String> maps = new HashMap<>();
        maps.put("Total-New-Balance",newBalance);

        return ResponseEntity.ok().body(maps);
    }


    @PutMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestHeader String senderAccountNo,
                                           @RequestHeader String receiverAccountNo,
                                           @RequestHeader double amount) throws AccountUnverified {

        var senderAccount = accountService.getAccount(senderAccountNo);
        var receiverAccount = accountService.getAccount(receiverAccountNo);
        String newBalance = accountService.transferMoney(senderAccount,receiverAccount,
                amount);
        Map<String ,String> maps = new HashMap<>();
        maps.put("Total-New-Balance after Transfer",newBalance);
        return ResponseEntity.ok().body(maps);
    }
}
