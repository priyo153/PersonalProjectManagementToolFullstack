package com.demoProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demoProject.entities.User;
import com.demoProject.exceptions.UsernameAlreadyExistsException;
import com.demoProject.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bcrypt;
	
	public User saveUser(User newUser) {
		newUser.setPassword(bcrypt.encode(newUser.getPassword()));
		
		try {
		return userRepository.save(newUser);
		}
		catch(DataIntegrityViolationException e) {
			throw new UsernameAlreadyExistsException();
		}
		
	}
}
