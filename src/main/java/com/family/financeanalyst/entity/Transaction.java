package com.family.financeanalyst.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "transactions", indexes = {
    @Index(name = "idx_transaction_duplicate", columnList = "description_1,transaction_date,amount")
})
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_type")
    private String accountType;

    @NotNull(message = "Transaction date is required")
    @Column(name = "transaction_date", nullable = false)
    private Instant transactionDate;

    @Column(name = "cheque_number")
    private String chequeNumber;

    @NotNull(message = "Description is required")
    @Column(name = "description_1", nullable = false)
    private String description1;

    @Column(name = "description_2")
    private String description2;

    @NotNull(message = "Amount is required")
    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "is_tracked")
    private Boolean isTracked = false;

    @NotNull(message = "Created date is required")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    // One-to-One relationship with Income
    @OneToOne
    @JoinColumn(name = "income_id")
    private Income income;

    // One-to-One relationship with Expense
    @OneToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;

    // Reference to the original transaction if this is a duplicate
    // NULL means this is the original transaction
    @ManyToOne
    @JoinColumn(name = "original_transaction_id")
    private Transaction originalTransaction;

    public Transaction(Instant transactionDate, String description1, Long amount) {
        this.transactionDate = transactionDate;
        this.description1 = description1;
        this.amount = amount;
        this.isTracked = false;
    }

    /**
     * JPA lifecycle callback to set createdAt timestamp before persisting
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Instant getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Boolean getIsTracked() {
        return isTracked;
    }

    public void setIsTracked(Boolean isTracked) {
        this.isTracked = isTracked;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
        if (income != null) {
            this.expense = null; // A transaction cannot be both income and expense
            this.isTracked = true;
        }
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
        if (expense != null) {
            this.income = null; // A transaction cannot be both income and expense
            this.isTracked = true;
        }
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Transaction getOriginalTransaction() {
        return originalTransaction;
    }

    public void setOriginalTransaction(Transaction originalTransaction) {
        this.originalTransaction = originalTransaction;
    }

    /**
     * Check if this transaction is a duplicate of another
     */
    public boolean isDuplicate() {
        return originalTransaction != null;
    }

    /**
     * Check if this transaction is the original (not a duplicate)
     */
    public boolean isOriginal() {
        return originalTransaction == null;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                ", transactionDate=" + transactionDate +
                ", description1='" + description1 + '\'' +
                ", amount=" + amount +
                ", isTracked=" + isTracked +
                ", createdAt=" + createdAt +
                '}';
    }
}
