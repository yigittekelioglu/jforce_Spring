package com.jforce_staj.ws.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
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

	
	public void assignAdminRoleToAdminUser() {
	    User adminUser = userRepository.findByUsername("admin");
	    if (adminUser != null) {
	        Role adminRole = roleRepository.findByName(Role.RoleType.ADMIN)
	            .orElseThrow(() -> new RuntimeException("Admin rolü bulunamadı!"));
	        
	        
	        if (!adminUser.getRoles().contains(adminRole)) {
	            adminUser.getRoles().add(adminRole);
	            userRepository.save(adminUser);
	            log.info("Admin kullanıcısına ADMIN rolü başarıyla atandı.");
	        } else {
	            log.info("Admin kullanıcısı zaten ADMIN rolüne sahip.");
	        }
	    } else {
	        log.error("Admin kullanıcısı bulunamadı!");
	    }
	}


}
