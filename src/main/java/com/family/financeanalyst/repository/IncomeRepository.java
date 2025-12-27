package com.family.financeanalyst.repository;

import com.family.financeanalyst.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Income entity
 * 
 * Provides CRUD operations and custom queries for Income categories.
 */
@Repository
@RepositoryRestResource(path = "incomes")
public interface IncomeRepository extends JpaRepository<Income, Long> {

    /**
     * Find incomes by category
     */
    List<Income> findByCategory(String category);

    /**
     * Find incomes with search string containing the given text
     */
    List<Income> findBySearchString1ContainingIgnoreCase(String searchText);

    /**
     * Find income by name
     */
    Income findByName(String name);
}
