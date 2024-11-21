package com.ng.NgBank.controller;

import com.ng.NgBank.entity.Account;
import com.ng.NgBank.response.CommonResponse;
import com.ng.NgBank.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin Services")
public class AdminController {

    private final AccountService accountService;

    @PutMapping("/verify-account")
    public CommonResponse verifyAccount(@RequestHeader String accountNo) {
        Account account = accountService.getAccount(accountNo);
        String status = accountService.verifyAccount(account);
        return new CommonResponse(status,null);

    }
}
