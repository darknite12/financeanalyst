package org.diytechprojects.financeanalyst.controller;

import org.diytechprojects.financeanalyst.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@GetMapping("/transactions/categorize")
	public void categorize() {

		transactionService.categorizeTransaction();

	}
}
