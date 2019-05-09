package com.banking.Request;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class TransactionsRequest {
	
	@JsonProperty(required = true)
	private BigDecimal amount;

	@JsonProperty(required = true)
	private Long fromAccountNumber;

	@JsonProperty(required = true)
	private Long toAccountNumber;

	public TransactionsRequest() {
		
	}
	
	public TransactionsRequest(BigDecimal amount, Long fromAccountNumber, Long toAccountNumber) {
		this.amount = amount;
		this.fromAccountNumber = fromAccountNumber;
		this.toAccountNumber = toAccountNumber;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(Long fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public Long getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(Long toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}
	
	

}
