package com.family.financeanalyst.repository;

import com.family.financeanalyst.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * Repository for Transaction entity
 * 
 * Provides CRUD operations and custom queries for Transactions.
 * Supports pagination for infinite scroll.
 */
@Repository
@RepositoryRestResource(path = "transactions")
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Find transactions by tracked status
     */
    Page<Transaction> findByIsTracked(Boolean isTracked, Pageable pageable);

    /**
     * Find untracked transactions (not categorized)
     */
    List<Transaction> findByIsTrackedFalse();

    /**
     * Find transactions by date range
     */
    Page<Transaction> findByTransactionDateBetween(Instant startDate, Instant endDate, Pageable pageable);

    /**
     * Find transactions by description containing text
     */
    Page<Transaction> findByDescription1ContainingIgnoreCaseOrDescription2ContainingIgnoreCase(
            String description1, String description2, Pageable pageable);

    /**
     * Find transactions with income category
     */
    Page<Transaction> findByIncomeIsNotNull(Pageable pageable);

    /**
     * Find transactions with expense category
     */
    Page<Transaction> findByExpenseIsNotNull(Pageable pageable);

    /**
     * Find transactions by account type
     */
    Page<Transaction> findByAccountType(String accountType, Pageable pageable);
}
