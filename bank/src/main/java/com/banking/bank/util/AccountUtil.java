package com.banking.bank.util;

import com.banking.bank.entity.Account;
import com.banking.bank.exception.AccountNotFoundException;
import com.banking.bank.repository.AccountRepository;

public class AccountUtil {
    private AccountUtil() {}

    public static Account findByIdOrThrow(Long id, AccountRepository accountRepository){
        return accountRepository.findById(id).orElseThrow(()->new AccountNotFoundException("Account not found with ID: "+id));
    }

}
