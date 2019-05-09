package com.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.Entity.Account;

@Repository
public interface AccountRepository  extends JpaRepository<Account, Long> {

}
