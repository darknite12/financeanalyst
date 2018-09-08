package org.diytechprojects.financeanalyst.service;

import java.util.List;

import org.diytechprojects.financeanalyst.model.Expense;
import org.diytechprojects.financeanalyst.model.ExpenseRepository;
import org.diytechprojects.financeanalyst.model.Income;
import org.diytechprojects.financeanalyst.model.IncomeRepository;
import org.diytechprojects.financeanalyst.model.Transaction;
import org.diytechprojects.financeanalyst.model.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository repo;
	
	@Autowired
	IncomeRepository incomeRepository;
	
	@Autowired
	ExpenseRepository expenseRepository;

	public void categorizeTransaction() {

		List<Transaction> unanalizedTransactions = repo.findByIncomeNullAndExpenseNull();

		for (Transaction transaction : unanalizedTransactions) {
			if (transaction.getAmountCad() != null) {
				if (transaction.getAmountCad() > 0) {
					Iterable<Income> incomes = incomeRepository.findAll();
					for(Income income : incomes) {
						if (income.getSearchString1() != null && income.getSearchString2() != null) {
							if (transaction.getDescription1().contains(income.getSearchString1()) && transaction.getDescription2().contains(income.getSearchString2())) {
								transaction.setIncome(income);
							}
						} else if (income.getSearchString1() != null && income.getSearchString2() == null) {
							if (transaction.getDescription1().contains(income.getSearchString1())) {
								transaction.setIncome(income);
							}
						} else if (income.getSearchString1() == null && income.getSearchString2() != null) {
							if (transaction.getDescription2().contains(income.getSearchString2())) {
								transaction.setIncome(income);
							}
						}
					}
				} else {
					Iterable<Expense> expenses = expenseRepository.findAll();
					for(Expense expense : expenses) {
						if (expense.getSearchString1() != null && expense.getSearchString2() != null) {
							if (transaction.getDescription1().contains(expense.getSearchString1()) && transaction.getDescription2().contains(expense.getSearchString2())) {
								transaction.setExpense(expense);
							}
						} else if (expense.getSearchString1() != null && expense.getSearchString2() == null) {
							if (transaction.getDescription1().contains(expense.getSearchString1())) {
								transaction.setExpense(expense);
							}
						} else if (expense.getSearchString1() == null && expense.getSearchString2() != null) {
							if (transaction.getDescription2().contains(expense.getSearchString2())) {
								transaction.setExpense(expense);
							}
						}
					}
				}

			}
			repo.save(transaction);
		}
	}

}
