package com.jforce_staj.ws.auth;
import java.util.Base64;  
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import com.jforce_staj.ws.error.ApiError;
import com.jforce_staj.ws.shared.CurrentUser;
import com.jforce_staj.ws.shared.Views;
import com.jforce_staj.ws.user.User;
import com.jforce_staj.ws.user.UserController;
import com.jforce_staj.ws.user.UserRepository;

@RestController
public class AuthController {
	
	//private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepository;
	
	//PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	//@RequestHeader(name="Authorization") String authorization (response entity parantez içi)
	
	//@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/api/1.0/auth")
	@JsonView(Views.Base.class)
	ResponseEntity<?> handleAuthentication(Authentication authentication) {
		
		//(@CurrentUser User use
		
		/*
		if(authorization == null) {
			ApiError error = new ApiError(401, "Unauthorized request", "/api/1.0/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}*/
		
		
		
		User user = (User) authentication.getPrincipal();
		
		//String username = user.getUsername();
		
		//databseden user getiriyor
		
		//String base64encoded = authorization.split("Basic ")[1];
		//String decoded = new String(Base64.getDecoder().decode(base64encoded));
		//String[] parts = decoded.split(":"); //username ve passwordu splitliyor
		//String username = parts[0];
		//String password = parts[1];
		
		//User inDB = userRepository.findByUsername(username);
		
		/*
		
		if(inDB == null) {
			ApiError error = new ApiError(401, "Unauthorized username request", "/api/1.0/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}
		String hashedPassword = inDB.getPassword();
		
		if(!passwordEncoder.matches(password, hashedPassword)) {
			ApiError error = new ApiError(401, "Unauthorized password request", "/api/1.0/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}*/
		
		
		//Map<String, String> responseBody = new HashMap<>();
		//responseBody.put("username", inDB.getUsername());
		//responseBody.put("password", inDB.getPassword());
		
		//log.info(authorization);
		System.out.println("Kullanıcının Roller: " + user.getRole());
		System.out.println("Kimlik doğrulanan kullanıcı: " + user.getUsername());
		return ResponseEntity.ok(user);
	}
	
	
	
	
	/*
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	ApiError handleBadCredentialsException() {
		ApiError error = new ApiError(401, "Unauthorized username request", "/api/1.0/auth");
		return error;
		
	}*/
	
	
	

}
