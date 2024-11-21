package com.ng.NgBank.service;

import com.ng.NgBank.entity.Account;
import com.ng.NgBank.exception.AccountUnverified;
import com.ng.NgBank.exception.InvalidAccountNo;
import com.ng.NgBank.models.ReceipientDetails;
import com.ng.NgBank.repository.AccountRespository;
import com.ng.NgBank.util.TransactionType;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Data
@Service
public class AccountService {

    private final AccountRespository accountRespository;
    private final TransactionHistoryService transactionHistoryService;
    private final EmailService emailService;

    public String getBalance(Account account) throws AccountUnverified {
        checkAndDealWithAccountUnverified(account);
        return account.getBalance().toPlainString();

    }
    @Transactional
    public String depositMoney(Account account, double amount) throws AccountUnverified {

        checkAndDealWithAccountUnverified(account);
        BigDecimal depositAmount = new BigDecimal(amount);
        BigDecimal totalAmount = depositAmount.add(account.getBalance());
        account.setBalance(totalAmount);
        accountRespository.save(account);
        transactionHistoryService.saveTransactionHistory(TransactionType.DEPOSIT,account);
        sendDepositeEmail(depositAmount);
        return totalAmount.toPlainString();
    }



    @Transactional
    public String withdrawMoney(Account account, double amount) throws AccountUnverified {
        checkAndDealWithAccountUnverified(account);
        BigDecimal withdrawAmount = new BigDecimal(amount);
        var comparisonResult = account.getBalance().compareTo(withdrawAmount);

        if (comparisonResult == 1 || comparisonResult == 0) {
            BigDecimal newBalance =
                    account.getBalance().subtract(withdrawAmount);
            account.setBalance(newBalance);
            accountRespository.save(account);
            account.setBalance(newBalance);
            transactionHistoryService.saveTransactionHistory(TransactionType.WITHDRAW,account);
            sendWithDrawEmail(withdrawAmount);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return newBalance.toPlainString();
        }
        throw new RuntimeException("Insufficient balance");


    }



    @Transactional
    public String transferMoney(Account senderAccount,Account receiverAccount,
                                double amount) throws AccountUnverified {
checkAndDealWithAccountUnverified(senderAccount);
checkAndDealWithAccountUnverified(receiverAccount);


        BigDecimal withdrawAmount = new BigDecimal(amount);
        var comparisonResult = senderAccount.getBalance().compareTo(withdrawAmount);

        if (comparisonResult == 1 || comparisonResult == 0) {
            BigDecimal senderNewBalance = senderAccount.getBalance().subtract(withdrawAmount);
            senderAccount.setBalance(senderNewBalance);
            accountRespository.save(senderAccount);

            BigDecimal receiverNewBalance = receiverAccount.getBalance().add(withdrawAmount);
            receiverAccount.setBalance(receiverNewBalance);
            accountRespository.save(receiverAccount);
            transactionHistoryService.saveTransactionHistory(TransactionType.TRANSFER,
                    receiverAccount,senderAccount);
            sendTransferEmail(withdrawAmount);
            return senderNewBalance.toPlainString();
        }

        throw new RuntimeException("Insufficient balance");

    }

    private void sendTransferEmail(BigDecimal withdrawAmount) {
        UserDetails userDetails = EmailService.getUserDetailsFromSecurityContext();
        ReceipientDetails receipientDetails = ReceipientDetails.builder()
                .body("Your Account has been debited by " + withdrawAmount + """
                        
                        Yours,
                        NgBank
                        """)
                .subject("Amount Transfer")
                .receipentmail(userDetails.getUsername())
                .build();

        emailService.sendMessage(receipientDetails);
    }


    public Account getAccount(String accountNo){
        return accountRespository.findByAccountNo(accountNo).orElseThrow(()-> new InvalidAccountNo(
                "Invalid Account no"));
    }

    private void checkAndDealWithAccountUnverified(Account account) throws AccountUnverified {
        Boolean isVerified = account.isAccountVerified();

        if(!isVerified){
            throw  new AccountUnverified("Account is not verified, Contact your nearest branch");
        }
    }

    @Transactional
    public String verifyAccount(Account account) {
        accountRespository.verifyAccount(account.getAccountNo());
        return "Account Verified";
    }




    private void sendDepositeEmail(BigDecimal depositAmount) {
        UserDetails userDetails = EmailService.getUserDetailsFromSecurityContext();
        ReceipientDetails receipientDetails = ReceipientDetails.builder()
                .body("Your Account has been deposited by " + depositAmount + """
                        
                        Yours,
                        NgBank
                        """)
                .subject("Account Debited")
                .receipentmail(userDetails.getUsername())
                .build();

        emailService.sendMessage(receipientDetails);
    }

    private void sendWithDrawEmail(BigDecimal withdrawAmount) {
        UserDetails userDetails = EmailService.getUserDetailsFromSecurityContext();
        ReceipientDetails receipientDetails = ReceipientDetails.builder()
                .body("Your Account has been withdrawn by " + withdrawAmount + """
                        
                        Yours,
                        NgBank
                        """)
                .subject("Account Credited")
                .receipentmail(userDetails.getUsername())
                .build();

        emailService.sendMessage(receipientDetails);
    }


}
