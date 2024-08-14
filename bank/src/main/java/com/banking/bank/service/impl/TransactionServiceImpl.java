package com.banking.bank.service.impl;

import com.banking.bank.dto.TransactionRequestDTO;
import com.banking.bank.dto.TransferRequestDTO;
import com.banking.bank.entity.*;
import com.banking.bank.exception.InsufficientBalanceException;
import com.banking.bank.exception.SameAccountException;
import com.banking.bank.repository.AccountRepository;
import com.banking.bank.repository.AccountTransactionRepository;
import com.banking.bank.repository.TransactionRepository;
import com.banking.bank.repository.UserRepository;
import com.banking.bank.service.AccountService;
import com.banking.bank.service.TransactionService;
import com.banking.bank.util.AccountUtil;
import com.banking.bank.util.UserUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountTransactionRepository accountTransactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountService accountService, AccountRepository accountRepository, UserRepository userRepository, AccountTransactionRepository accountTransactionRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountTransactionRepository = accountTransactionRepository;
    }

    @Override
    @Transactional
    public BigDecimal deposit(TransactionRequestDTO transactionRequestDTO) {
        Account ownerAccount = AccountUtil.findByIdOrThrow(transactionRequestDTO.getAccountId(), accountRepository);
        UserUtil.findAndValidate(ownerAccount.getUser().getId(), transactionRequestDTO.getUsername(),
                transactionRequestDTO.getPassword(),userRepository);
        return processTransaction(ownerAccount,transactionRequestDTO.getAmount(),TransactionType.DEPOSIT,null);
    }

    @Override
    @Transactional
    public BigDecimal withdraw(TransactionRequestDTO transactionRequestDTO) {
        Account ownerAccount = AccountUtil.findByIdOrThrow(transactionRequestDTO.getAccountId(), accountRepository);
        UserUtil.findAndValidate(ownerAccount.getUser().getId(), transactionRequestDTO.getUsername(),
                transactionRequestDTO.getPassword(),userRepository);
        return processTransaction(ownerAccount,transactionRequestDTO.getAmount(),TransactionType.WITHDRAWAL,null);
    }

    @Override
    @Transactional
    public BigDecimal transfer(TransferRequestDTO transferRequestDTO) {
        Account ownerAccount = AccountUtil.findByIdOrThrow(transferRequestDTO.getSenderAccountId(),accountRepository);
        Account receiver = AccountUtil.findByIdOrThrow(transferRequestDTO.getReceiverAccountId(), accountRepository);
        UserUtil.findAndValidate(ownerAccount.getUser().getId(), transferRequestDTO.getUsername(),
                transferRequestDTO.getPassword(),userRepository);
        return processTransaction(ownerAccount,transferRequestDTO.getAmount(),TransactionType.TRANSFER,receiver);
    }

    @Override
    public List<Transaction> get(Long id) {
        AccountUtil.findByIdOrThrow(id,accountRepository);
        return transactionRepository.findAllByOwnerAccountId(id);
    }


    @Transactional
    public BigDecimal processTransaction(Account ownerAccount, BigDecimal amount, TransactionType transactionType, Account receiver) {
        BigDecimal newBalance = ownerAccount.getBalance();
        BigDecimal transactionAmount = amount;
        Transaction transaction = null;

        switch (transactionType) {
            case DEPOSIT:
                newBalance = newBalance.add(amount);
                transaction = new Transaction(transactionAmount, transactionType,ownerAccount,receiver);
                transactionRepository.save(transaction);
                break;
            case WITHDRAWAL:
                validateSufficientBalance(ownerAccount, amount);
                newBalance = newBalance.subtract(amount);
                transactionAmount = amount.negate();
                transaction = new Transaction(transactionAmount, transactionType,ownerAccount,receiver);
                transactionRepository.save(transaction);
                break;
            case TRANSFER:
                validateSufficientBalance(ownerAccount, amount);
                validateDifferentAccounts(ownerAccount, receiver);
                newBalance = newBalance.subtract(amount);
                receiver.setBalance(receiver.getBalance().add(amount));
                accountRepository.save(receiver);
                transaction = new Transaction(transactionAmount, TransactionType.TRANSFER, ownerAccount, receiver);
                transactionRepository.save(transaction);
                AccountTransaction receiverTransaction = new AccountTransaction(receiver, transaction);
                accountTransactionRepository.save(receiverTransaction);
                break;
        }
        AccountTransaction ownerTransaction = new AccountTransaction(ownerAccount, transaction);
        accountTransactionRepository.save(ownerTransaction);
        ownerAccount.setBalance(newBalance);
        accountRepository.save(ownerAccount);
        return newBalance;
    }

    private void validateSufficientBalance(Account account, BigDecimal amount) {
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance to complete the transaction.");
        }
    }

    private void validateDifferentAccounts(Account sender, Account receiver) {
        if (sender.getId().equals(receiver.getId())) {
            throw new SameAccountException("Sender and receiver account cannot be the same.");
        }
    }

}
