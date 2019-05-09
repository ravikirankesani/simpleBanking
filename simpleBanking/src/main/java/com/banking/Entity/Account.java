package com.banking.Entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ACCOUNT")
public class Account {
	
	@Id
	@Column(name="ACCOUNT_NUMBER")
	private Long accountNumber;
	@Column(name="USER_ID")
	private String userId;
	@Column(name="ACCOUNT_TYPE")
	private String accountType;
	@Column(name="BALANCE")
	private BigDecimal balance = BigDecimal.ZERO;
	@Column(name="MODIFIED_BY")
	private String modifiedBy;
	
	
	public Account() {
		
	}
	
	public Account(Long accountNumber, String userId, String accountType, BigDecimal balance, String modifiedBy) {
		
		this.accountNumber = accountNumber;
		this.userId = userId;
		this.accountType = accountType;
		this.balance = balance;
		this.modifiedBy = modifiedBy;
	}
	
	
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
