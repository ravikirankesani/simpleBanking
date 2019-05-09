package com.banking.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.Entity.User;
import com.banking.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired 
	UserRepository userRepository;

	
	public List<User> getAllUsers() {
		  List<User> users = userRepository.findAll();
		  return users;
		 }
		  
		 public User getUser(String userId) {
		  Optional<User> userInfo = userRepository.findById(userId);
		  return userInfo.get();
		 }
		 
		 public User saveUser(User userInfo) {
			 User savedUser = userRepository.save(userInfo);
			  return savedUser;
		 }
}
