package com.family.financeanalyst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application for Family Financial Analyst
 * 
 * This application helps users track, categorize, and analyze 
 * their financial transactions from multiple banking sources.
 */
@SpringBootApplication
public class FinanceAnalystApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceAnalystApplication.class, args);
    }
}
