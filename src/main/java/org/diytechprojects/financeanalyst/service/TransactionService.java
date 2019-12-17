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

		List<Transaction> untrackedTransactions = repo.findByIsTrackedNull();

		for (Transaction transaction : untrackedTransactions) {
			if (transaction.getAmountCad() != null) {
				if (transaction.getAmountCad() > 0) {
					Iterable<Income> incomes = incomeRepository.findAll();
					for(Income income : incomes) {
						if (isTransactionTracked(transaction, income)) {
							transaction.setIncome(income);
							transaction.setIsTracked(true);
						}
					}
				} else {
					Iterable<Expense> expenses = expenseRepository.findAll();
					for(Expense expense : expenses) {
						if (isTransactionTracked(transaction, expense)) {
							transaction.setExpense(expense);
							transaction.setIsTracked(true);
						}
					}
				}
			}
			repo.save(transaction);
		}
	}
	
	private boolean isTransactionTracked(Transaction transaction, Object transactionType) {
		boolean isTracked  = false;
		if (transactionType instanceof Income) {
			Income income = (Income) transactionType;
			if (income.getSearchString1() != null && income.getSearchString2() != null) {
				isTracked = transaction.getDescription1().contains(income.getSearchString1()) && transaction.getDescription2().contains(income.getSearchString2());
			} else if (income.getSearchString1() != null && income.getSearchString2() == null) {
				isTracked = transaction.getDescription1().contains(income.getSearchString1());
			} else if (income.getSearchString1() == null && income.getSearchString2() != null) {
				isTracked = transaction.getDescription2().contains(income.getSearchString2());
			}
		} else if (transactionType instanceof Expense) {
			Expense expense = (Expense) transactionType;
			if (expense.getSearchString1() != null && expense.getSearchString2() != null) {
				isTracked = transaction.getDescription1().contains(expense.getSearchString1()) && transaction.getDescription2().contains(expense.getSearchString2());
			} else if (expense.getSearchString1() != null && expense.getSearchString2() == null) {
				isTracked = transaction.getDescription1().contains(expense.getSearchString1());
			} else if (expense.getSearchString1() == null && expense.getSearchString2() != null) {
				isTracked = transaction.getDescription2().contains(expense.getSearchString2());
			}
		}
		return isTracked;
		
	}

}
