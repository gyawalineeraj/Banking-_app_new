package com.ng.NgBank.response;


import com.ng.NgBank.util.AccountType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String accountNo;
    private boolean isAccountVerified;
    private BigDecimal balance;
    private AccountType accountType;
    private BigDecimal creditAvailiable;

}
