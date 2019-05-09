package com.banking.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.banking.Entity.Account;
import com.banking.Request.TransactionsRequest;
import com.banking.Service.AccountService;
import com.banking.validator.AccountRequestValidator;

@RestController
public class AccountTransactionsController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountRequestValidator accountRequestValidator;
	
	@PostMapping("/simpleBanking/withdrawl")
	public ResponseEntity<Object> withdrawlFromAccount(@RequestBody TransactionsRequest requestBody) throws Exception {
		
		accountRequestValidator.validateWithdrawlOrDeposit(requestBody.getFromAccountNumber());
		
		Account savedAccount = accountService.updateWithdrawlAmount(requestBody);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{fromAccountNumber}")
				.buildAndExpand(savedAccount.getAccountNumber()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PostMapping("/simpleBanking/deposit")
	public ResponseEntity<Object> depositToAccount(@RequestBody TransactionsRequest requestBody) throws Exception {
		
		accountRequestValidator.validateWithdrawlOrDeposit(requestBody.getToAccountNumber());
		
		Account savedAccount = accountService.updateDepositAmount(requestBody);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{toAccountNumber}")
				.buildAndExpand(savedAccount.getAccountNumber()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PostMapping("/simpleBanking/accountToAccountTransfer")
	public ResponseEntity<Object> transferBetweenAccounts(@RequestBody TransactionsRequest requestBody) throws Exception {
		
		accountRequestValidator.validateAccountTransfer(requestBody);
		
		Account savedAccount = accountService.transferBetweenAccounts(requestBody);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{toAccountNumber}")
				.buildAndExpand(savedAccount.getAccountNumber()).toUri();

		return ResponseEntity.created(location).build();

	}

}
