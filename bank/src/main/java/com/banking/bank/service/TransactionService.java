package com.banking.bank.service;

import com.banking.bank.dto.TransactionRequestDTO;
import com.banking.bank.dto.TransferRequestDTO;
import com.banking.bank.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    BigDecimal deposit(TransactionRequestDTO transactionRequestDTO);

    BigDecimal withdraw(TransactionRequestDTO transactionRequestDTO);

    BigDecimal transfer(TransferRequestDTO transferRequestDTO);

    List<Transaction> get(Long id);
}
