package org.diytechprojects.financeanalyst.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the expense database table.
 * 
 */
@Entity
@NamedQuery(name="Expense.findAll", query="SELECT e FROM Expense e")
public class Expense implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="expense_id")
	private String expenseId;

	private String catagory;

	private String name;
	
	@Column(name="search_string_1")
	private String searchString1;

	@Column(name="search_string_2")
	private String searchString2;


	public Expense() {
	}

	public String getExpenseId() {
		return this.expenseId;
	}

	public void setExpenseId(String expenseId) {
		this.expenseId = expenseId;
	}

	public String getCatagory() {
		return this.catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
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

}