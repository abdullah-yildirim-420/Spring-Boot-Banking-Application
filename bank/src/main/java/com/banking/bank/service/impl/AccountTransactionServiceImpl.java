package com.banking.bank.service.impl;

import com.banking.bank.repository.AccountTransactionRepository;
import com.banking.bank.service.AccountTransactionService;
import org.springframework.stereotype.Service;

@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {

    private final AccountTransactionRepository accountTransactionRepository;

    public AccountTransactionServiceImpl(AccountTransactionRepository accountTransactionRepository) {
        this.accountTransactionRepository = accountTransactionRepository;
    }



}
