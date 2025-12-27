package com.family.financeanalyst.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Expense Entity
 * 
 * Represents expense categories for financial transactions.
 * Used to categorize transactions as various types of expenses.
 */
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category is required")
    @Column(nullable = false)
    private String category;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @Column(name = "search_string_1")
    private String searchString1;

    // Constructors
    public Expense() {
    }

    public Expense(String category, String name) {
        this.category = category;
        this.name = name;
    }

    public Expense(String category, String name, String searchString1) {
        this.category = category;
        this.name = name;
        this.searchString1 = searchString1;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchString1() {
        return searchString1;
    }

    public void setSearchString1(String searchString1) {
        this.searchString1 = searchString1;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", searchString1='" + searchString1 + '\'' +
                '}';
    }
}
