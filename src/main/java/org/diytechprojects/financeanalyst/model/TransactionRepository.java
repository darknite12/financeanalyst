package org.diytechprojects.financeanalyst.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	List<Transaction> findByIncomeNullAndExpenseNull();

}
