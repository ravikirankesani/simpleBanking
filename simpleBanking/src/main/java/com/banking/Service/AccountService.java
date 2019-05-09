package com.banking.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.Entity.Account;
import com.banking.Repository.AccountRepository;
import com.banking.Request.TransactionsRequest;

@Service
public class AccountService {
	
	@Autowired 
	AccountRepository accountRepository;

	
		 public Account getAccountByNumber(Long accountNumber) {
		  Optional<Account> optAccount = accountRepository.findById(accountNumber);
		  return optAccount.get();
		 }
		 
		 public Account saveAccount(Account accountInfo) {
			 Account savedAccount = accountRepository.save(accountInfo);
			  return savedAccount;
		 }

		public Account updateWithdrawlAmount(TransactionsRequest requestBody) throws Exception {
			
			Account accountInfo = getAccountByNumber(requestBody.getFromAccountNumber());
			if (accountInfo.getBalance().compareTo(requestBody.getAmount()) >= 0) {
				accountInfo.setBalance(accountInfo.getBalance().subtract(requestBody.getAmount()));
				accountRepository.save(accountInfo);
			} else {
				throw new Exception("Does not have sufficient balance in the account");
			}
			return accountInfo;
		}
		
		public Account updateDepositAmount(TransactionsRequest requestBody) throws Exception {
			
			Account accountInfo = getAccountByNumber(requestBody.getToAccountNumber());
			if (requestBody.getAmount().doubleValue() >= 0) {
				accountInfo.setBalance(accountInfo.getBalance().add(requestBody.getAmount()));
				accountRepository.save(accountInfo);
			} else {
				throw new Exception("Amount Specified in the request is invalid");
			}
			return accountInfo;
		}
		
		public Account transferBetweenAccounts(TransactionsRequest requestBody) throws Exception {
			
			Account fromAccountInfo = getAccountByNumber(requestBody.getFromAccountNumber());
			Account toAccountInfo = getAccountByNumber(requestBody.getToAccountNumber());
			if (fromAccountInfo.getBalance().compareTo(requestBody.getAmount()) >= 0) {
				toAccountInfo.setBalance(toAccountInfo.getBalance().add(fromAccountInfo.getBalance()));
				fromAccountInfo.setBalance(fromAccountInfo.getBalance().subtract(requestBody.getAmount()));
				accountRepository.save(toAccountInfo);
				accountRepository.save(fromAccountInfo);
			} else {
				throw new Exception("Transfer Amount is greater than the available Balance");
			}
			return toAccountInfo;
		}
}
