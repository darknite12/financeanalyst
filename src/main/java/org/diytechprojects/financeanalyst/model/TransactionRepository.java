package org.diytechprojects.financeanalyst.model;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	List<Transaction> findByIsTrackedNull();

	@Query("select t from Transaction t where t.transactionDate >= :fromDate and t.transactionDate <= :toDate and t.isTracked=true and t.expense is not null")
	List<Transaction> findExpenseTrackedTransactioBetween(@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

}
