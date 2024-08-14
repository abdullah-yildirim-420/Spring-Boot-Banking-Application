package com.banking.bank.service.impl;

import com.banking.bank.dto.AccountCreateDTO;
import com.banking.bank.dto.AccountResponseDTO;
import com.banking.bank.entity.Account;
import com.banking.bank.entity.User;
import com.banking.bank.mapper.AccountMapper;
import com.banking.bank.repository.AccountRepository;
import com.banking.bank.repository.UserRepository;
import com.banking.bank.service.AccountService;
import com.banking.bank.util.AccountUtil;
import com.banking.bank.util.UserUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper ;


    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    @Transactional
    public AccountResponseDTO create(AccountCreateDTO accountCreateDTO) {
        User user = UserUtil.findAndValidate(accountCreateDTO.getUserId(),accountCreateDTO.getUsername(),
                accountCreateDTO.getPassword(),userRepository);
        Account account = new Account(user);
        accountRepository.save(account);
        return accountMapper.toDto(account);
    }

    @Override
    public AccountResponseDTO get(Long id) {
        Account account = AccountUtil.findByIdOrThrow(id,accountRepository);
        return accountMapper.toDto(account);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        AccountUtil.findByIdOrThrow(id,accountRepository);
        accountRepository.deleteById(id);
    }

}
