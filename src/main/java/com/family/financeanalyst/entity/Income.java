package com.family.financeanalyst.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Income Entity
 * 
 * Represents income categories for financial transactions.
 * Used to categorize transactions as various types of income.
 */
@Entity
@Table(name = "incomes")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Category is required")
    @Column(nullable = false)
    private String category;

    @Column(name = "search_string_1")
    private String searchString1;

    // Constructors
    public Income() {
    }

    public Income(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public Income(String name, String category, String searchString1) {
        this.name = name;
        this.category = category;
        this.searchString1 = searchString1;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSearchString1() {
        return searchString1;
    }

    public void setSearchString1(String searchString1) {
        this.searchString1 = searchString1;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", searchString1='" + searchString1 + '\'' +
                '}';
    }
}
