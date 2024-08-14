package com.banking.bank.controller;

import com.banking.bank.dto.AccountCreateDTO;
import com.banking.bank.dto.AccountResponseDTO;
import com.banking.bank.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> create(@Valid @RequestBody AccountCreateDTO accountCreateDTO){
        AccountResponseDTO accountResponseDTO = accountService.create(accountCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> get(@PathVariable Long id){
        AccountResponseDTO accountResponseDTO = accountService.get(id);
        return ResponseEntity.ok(accountResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
