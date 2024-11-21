package com.ng.NgBank.entity;

import com.ng.NgBank.util.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account extends Base {

    private String accountNo;
    private boolean isAccountVerified;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private BigDecimal creditAvailiable = new BigDecimal(0);

    @Version
    private int version;

    @OneToOne(mappedBy = "account")
    private User user;


    @OneToMany(mappedBy = "account")
    private List<TransactionHistory> transactionHistories;

}
