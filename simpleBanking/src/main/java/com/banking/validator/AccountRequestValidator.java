package com.banking.validator;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.banking.Entity.Account;
import com.banking.Exception.IdNotFoundException;
import com.banking.Request.TransactionsRequest;

@Component
public class AccountRequestValidator {
	
public void validate(Account accountInfo) {
		
		if (StringUtils.isEmpty(accountInfo.getUserId())) {
			throw new IdNotFoundException("User ID is required for adding an Account");
		}
		
		if (StringUtils.isEmpty(accountInfo.getAccountNumber())) {
			throw new IdNotFoundException("Account Number is required for adding an Account");
		}
		
		if (StringUtils.isEmpty(accountInfo.getAccountType())) {
			throw new IdNotFoundException("Account Type is required for adding an Account");
		}
		
	}


public void validateWithdrawlOrDeposit(Long accountNumber) {
	
	if (Objects.isNull(accountNumber)) {
		throw new IdNotFoundException("Withdrawl Account Number is Missing in the request");
	}
	
}

public void validateAccountTransfer(TransactionsRequest requestBody) {
	
	if (Objects.isNull(requestBody.getFromAccountNumber())) {
		throw new IdNotFoundException("Transfer From Account Number is Missing in the request");
	}
	
	if (Objects.isNull(requestBody.getToAccountNumber())) {
		throw new IdNotFoundException("Transfer to Account Number is Missing in the request");
	}
	
}



}
