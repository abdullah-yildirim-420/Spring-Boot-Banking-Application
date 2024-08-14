package com.banking.bank.mapper;

import com.banking.bank.dto.AccountResponseDTO;
import com.banking.bank.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountResponseDTO toDto(Account account) {
        return new AccountResponseDTO(
                account.getId(),
                account.getUser().getId(),
                account.getUser().getFirstName(),
                account.getUser().getLastName(),
                account.getBalance(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }
}
