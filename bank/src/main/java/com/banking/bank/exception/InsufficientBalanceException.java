package com.banking.bank.exception;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException() {
        super("Insufficient balance for this transaction.");
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }

}
