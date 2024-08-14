package com.banking.bank.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;
    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_account_id", nullable = false)
    @JsonBackReference
    private Account ownerAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_account_id", nullable = true)
    @JsonBackReference
    private Account receiverAccount;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<AccountTransaction> accountTransactions = new ArrayList<>();


    public Transaction() {

    }

    public Transaction(BigDecimal amount, TransactionType transactionType, Account ownerAccount, Account receiverAccount) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.ownerAccount = ownerAccount;
        this.receiverAccount = receiverAccount;
    }

    @PrePersist
    protected void onCreate() {
        transactionDate = LocalDateTime.now();
    }
}
