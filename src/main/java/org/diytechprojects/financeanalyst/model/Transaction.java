package org.diytechprojects.financeanalyst.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="transaction_id")
	private String transactionId;

	@Column(name="account_number")
	private String accountNumber;

	@Column(name="account_type")
	private String accountType;

	@Column(name="amount_cad")
	private Float amountCad;

	@Column(name="amount_usd")
	private Float amountUsd;

	@Column(name="cheque_number")
	private String chequeNumber;

	@Column(name="description_1")
	private String description1;

	@Column(name="description_2")
	private String description2;

	@Temporal(TemporalType.DATE)
	@Column(name="transaction_date")
	private Date transactionDate;

	//bi-directional many-to-one association to Expense
	@ManyToOne
	@JoinColumn(name="expense_id")
	private Expense expense;

	//bi-directional many-to-one association to Income
	@ManyToOne
	@JoinColumn(name="income_id")
	private Income income;

	public Transaction() {
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Float getAmountCad() {
		return this.amountCad;
	}

	public void setAmountCad(Float amountCad) {
		this.amountCad = amountCad;
	}

	public Float getAmountUsd() {
		return this.amountUsd;
	}

	public void setAmountUsd(Float amountUsd) {
		this.amountUsd = amountUsd;
	}

	public String getChequeNumber() {
		return this.chequeNumber;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public String getDescription1() {
		return this.description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public String getDescription2() {
		return this.description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Expense getExpense() {
		return this.expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public Income getIncome() {
		return this.income;
	}

	public void setIncome(Income income) {
		this.income = income;
	}

}