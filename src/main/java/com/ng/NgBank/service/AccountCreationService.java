package com.ng.NgBank.service;

import com.ng.NgBank.entity.Account;
import com.ng.NgBank.repository.AccountRespository;
import com.ng.NgBank.util.AccountType;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;

@Data
@Service
public class AccountCreationService {

    private final AccountRespository accountRespository;

    public Account getSavingAccount(double balance,double credit){
        return Account.builder()
                .accountNo(getUniqueAccountNo())
                .accountType(AccountType.SAVING)
                .isAccountVerified(false)
                .balance(new BigDecimal(balance))
                .creditAvailiable(new BigDecimal(credit))
                .build();
    }

    private String getAccountNo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ng11");
        SecureRandom secureRandom = new SecureRandom();
        for(int i = 0; i < 8; i++){
            var n = secureRandom.nextInt(10);
            stringBuilder.append(n);
        }
        return stringBuilder.toString();
    }

    private String getUniqueAccountNo(){
        var accountNo = getAccountNo();
        var accountExist = accountRespository.existsByAccountNo(accountNo);
        if(accountExist){
            getUniqueAccountNo();
        }
        return accountNo;

    }


}
