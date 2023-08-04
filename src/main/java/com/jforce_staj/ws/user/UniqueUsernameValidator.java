package com.jforce_staj.ws.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Component;




@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{
	
	
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepository;

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		log.info("Validating unique username: " + username);
		User user = userRepository.findByUsername(username);
		if(user != null) {
			
			log.warn("Username is already in use: " + username);
			return false;
		}
		return true;
	}

}

//Sıkıntılı class
