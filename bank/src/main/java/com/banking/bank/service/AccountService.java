package com.banking.bank.service;

import com.banking.bank.dto.AccountCreateDTO;
import com.banking.bank.dto.AccountResponseDTO;
import com.banking.bank.entity.Account;

public interface AccountService {

    AccountResponseDTO create(AccountCreateDTO accountCreateDTO);

    AccountResponseDTO get(Long id);

    void delete(Long id);
}
