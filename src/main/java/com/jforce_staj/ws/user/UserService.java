package com.jforce_staj.ws.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jforce_staj.ws.role.Role;
import com.jforce_staj.ws.role.RoleRepository;

@Service
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepository;
	
	
	
	PasswordEncoder passwordEncoder;

	
	@Autowired //1 class'da 1 constructor varsa ve parametreler alabiliyorsa autowired'a gerek yok.
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}


	/*
	public void save(User user) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword())); 
		userRepository.save(user);
		
	}*/
	
	public void save(User user) {
	    log.info("Saving user: " + user.getUsername());
	    try {
	        user.setPassword(this.passwordEncoder.encode(user.getPassword())); 
	        userRepository.save(user);
	    } catch (Exception e) {
	        log.error("Error while saving user: " + user.getUsername(), e);
	    }
	    //log.info("User saved successfully: " + user.getUsername());
	}

	
	

}
