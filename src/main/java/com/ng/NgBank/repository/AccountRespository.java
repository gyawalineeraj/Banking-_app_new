package com.ng.NgBank.repository;

import com.ng.NgBank.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRespository extends JpaRepository<Account,Integer> {

    boolean existsByAccountNo(String accountNo);

    Optional<Account> findByAccountNo(String accountNo);

    @Query("""
            UPDATE Account a
            SET a.isAccountVerified = true
            WHERE a.accountNo = :accountNo
            """)
    @Modifying
    void verifyAccount(String accountNo);
}
