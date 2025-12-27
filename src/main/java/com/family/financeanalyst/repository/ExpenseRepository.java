package com.family.financeanalyst.repository;

import com.family.financeanalyst.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Expense entity
 * 
 * Provides CRUD operations and custom queries for Expense categories.
 */
@Repository
@RepositoryRestResource(path = "expenses")
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    /**
     * Find expenses by category
     */
    List<Expense> findByCategory(String category);

    /**
     * Find expenses with search string containing the given text
     */
    List<Expense> findBySearchString1ContainingIgnoreCase(String searchText);

    /**
     * Find expense by name
     */
    Expense findByName(String name);
}
