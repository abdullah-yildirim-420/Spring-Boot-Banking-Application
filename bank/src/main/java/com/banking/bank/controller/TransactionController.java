package com.banking.bank.controller;

import com.banking.bank.dto.TransactionRequestDTO;
import com.banking.bank.dto.TransferRequestDTO;
import com.banking.bank.entity.Transaction;
import com.banking.bank.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO){
        BigDecimal newBalance = transactionService.deposit(transactionRequestDTO);
        String message = "Successfully deposited "+ transactionRequestDTO.getAmount()+". New balance: "+newBalance;
        return ResponseEntity.ok(message);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO) {
        BigDecimal newBalance = transactionService.withdraw(transactionRequestDTO);
        String message = "Successfully withdrawn " + transactionRequestDTO.getAmount() + ". New balance: " + newBalance;
        return ResponseEntity.ok(message);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@Valid @RequestBody TransferRequestDTO transferRequestDTO){
        BigDecimal newBalance = transactionService.transfer(transferRequestDTO);
        String message = "Successfully transferred "+transferRequestDTO.getAmount()+ " to account "+transferRequestDTO.getReceiverAccountId()+". New balance: "+newBalance;
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Transaction>> get(@PathVariable Long id){
        List<Transaction> transactions = transactionService.get(id);
        return ResponseEntity.ok(transactions);
    }









}
