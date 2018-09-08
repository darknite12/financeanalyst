package org.diytechprojects.financeanalyst.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the income database table.
 * 
 */
@Entity
@NamedQuery(name="Income.findAll", query="SELECT i FROM Income i")
public class Income implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="income_id")
	private String incomeId;

	private String name;
	
	@Column(name="search_string_1")
	private String searchString1;

	@Column(name="search_string_2")
	private String searchString2;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="income")
	private List<Transaction> transactions;

	public Income() {
	}

	public String getIncomeId() {
		return this.incomeId;
	}

	public void setIncomeId(String incomeId) {
		this.incomeId = incomeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSearchString1() {
		return this.searchString1;
	}

	public void setSearchString1(String searchString1) {
		this.searchString1 = searchString1;
	}

	public String getSearchString2() {
		return this.searchString2;
	}

	public void setSearchString2(String searchString2) {
		this.searchString2 = searchString2;
	}


	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setIncome(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setIncome(null);

		return transaction;
	}

}