package com.banking.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.banking.Entity.User;
import com.banking.Exception.IdNotFoundException;

@Component
public class UserRequestValidator {
	
	public void validate(User userInfo) {
		
		if (StringUtils.isEmpty(userInfo.getUserId())) {
			throw new IdNotFoundException("User ID is required for adding a user");
		}
		
		if (StringUtils.isEmpty(userInfo.getEmail())) {
			throw new IdNotFoundException("Email ID is required for adding a user");
		}
		
		if (StringUtils.isEmpty(userInfo.getFirstName())) {
			throw new IdNotFoundException("User First Name is required for adding a user");
		}
		
		if (StringUtils.isEmpty(userInfo.getLastName())) {
			throw new IdNotFoundException("User Last Name is required for adding a user");
		}
		
		if (StringUtils.isEmpty(userInfo.getAddressLine1())) {
			throw new IdNotFoundException("User Address Information is required for adding a user");
		}
	}

}
