package com.banking.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.banking.Entity.User;
import com.banking.Exception.IdNotFoundException;
import com.banking.Service.UserService;
import com.banking.validator.UserRequestValidator;

@RestController
public class UserController {
	
	@Autowired
	 private UserService userService;
	@Autowired
	private UserRequestValidator userRequestValidator;
	
	@GetMapping("/simpleBanking/users")
	 public List<User> getUsers() {
	  List<User> users = userService.getAllUsers();
	  return users;
	 }
	
	@GetMapping("/simpleBanking/users/{userId}/")
	 public User getUserById(@PathVariable(name="userId")String userId) {
	  User userInfo = userService.getUser(userId);
	  
	  if (StringUtils.isEmpty(userInfo.getUserId()))
			throw new IdNotFoundException("No Information Exists for the User Id - " + userId);
		return userInfo;
	 }
	
	
	
	@PostMapping("/simpleBanking/users")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		
		userRequestValidator.validate(user);
		User savedUser = userService.saveUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
				.buildAndExpand(savedUser.getUserId()).toUri();

		return ResponseEntity.created(location).build();

	}

}
