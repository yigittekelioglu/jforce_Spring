package com.jforce_staj.ws.user;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger; 

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jforce_staj.ws.error.ApiError;
import com.jforce_staj.ws.shared.GenericResponse;

@RestController
public class UserController {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	//@CrossOrigin //cross yapıyor cıfte lınk
	@PostMapping("/api/1.0/users")
	@ResponseStatus(HttpStatus.CREATED)
	public GenericResponse createUser(@Valid @RequestBody User user) {
		
		
		 /* bu kısım response entity type'ı ile, manuel olarak kontrol etmeye yarıyor validationları /ResponseEntity<?>
		  * 
		  
		 ApiError error = new ApiError(400, "Validation error", "/api/1.0/users");
		 Map<String, String> validationErrors = new HashMap<>();
		
		 String username = user.getUsername();
		 if(username == null || username.isEmpty()) {
			 validationErrors.put("username", "Username cannot be empty.");
		 }
		 
		 
		 if(validationErrors.size() > 0) {
			 error.setValidationErrors(validationErrors);
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		 }
		 */
		 
		 userService.save(user);
		 return new GenericResponse("User Created.");
		
		 //ResponseEntity<?>
		
		/*
		 userService.save(user);
		 log.info(user.toString()); 
		 return new GenericResponse("User created.");
		 //response.setMessage("User created.");
		 //return response;*/
	} 
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleValidationException(MethodArgumentNotValidException exception) {
		 ApiError error = new ApiError(400, "Validation error", "/api/1.0/users");
		 Map<String, String> validationErrors = new HashMap<>();
		 for(FieldError fieldError: exception.getBindingResult().getFieldErrors()) {
			 validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		 }
		 error.setValidationErrors(validationErrors);
		 return error;
	 }

}
