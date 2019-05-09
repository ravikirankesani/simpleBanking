package com.banking.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.banking.Entity.Account;
import com.banking.Exception.IdNotFoundException;
import com.banking.Service.AccountService;
import com.banking.validator.AccountRequestValidator;

@RestController
public class AccountController {
	
	@Autowired
	 private AccountService accountService;
	@Autowired
	private AccountRequestValidator accountRequestValidator;
	
	@GetMapping("/simpleBanking/accounts/{accountNumber}")
	 public Account getAccountByNumber(@PathVariable(name="accountNumber")Long accountNumber) {
		 Account accountInfo = accountService.getAccountByNumber(accountNumber);
		if (StringUtils.isEmpty(accountInfo.getAccountNumber()))
			throw new IdNotFoundException("No Information Exists for the Account Number  - " + accountNumber);

		return accountInfo;
	 }
	
	
	
	@PostMapping("/simpleBanking/account")
	public ResponseEntity<Object> addAccount(@RequestBody Account account) {
		
		accountRequestValidator.validate(account);
		
		Account savedAccount = accountService.saveAccount(account);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{accountNumber}")
				.buildAndExpand(savedAccount.getAccountNumber()).toUri();

		return ResponseEntity.created(location).build();

	}

}
