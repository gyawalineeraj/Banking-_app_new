package com.ng.NgBank.service;

import com.ng.NgBank.entity.Account;
import com.ng.NgBank.entity.TransactionHistory;
import com.ng.NgBank.repository.AccountRespository;
import com.ng.NgBank.repository.TranscationHistoryRepository;
import com.ng.NgBank.util.TransactionType;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Data
@Service
public class TransactionHistoryService {

    private final TranscationHistoryRepository transcationHistoryRepository;
    private final AccountRespository accountRespository;

    public void saveTransactionHistory(TransactionType type, Account account) {
        TransactionHistory transactionHistory = new TransactionHistory(type,
                account);
        transcationHistoryRepository.save(transactionHistory);

    }

    public void saveTransactionHistory(TransactionType type, Account receiverAccount,
                                       Account senderAccount) {

        TransactionHistory transactionHistoryReciever = new TransactionHistory(type,
                receiverAccount);


        TransactionHistory transactionHistorySender = new TransactionHistory(type,
                senderAccount);

        transcationHistoryRepository.save(transactionHistoryReciever);
        transcationHistoryRepository.save(transactionHistorySender);

    }


}
