package com.jforce_staj.ws.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger; 
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jforce_staj.ws.error.ApiError;
import com.jforce_staj.ws.role.Role;
import com.jforce_staj.ws.role.Role.RoleType;
import com.jforce_staj.ws.role.RoleRepository;
import com.jforce_staj.ws.shared.GenericResponse;

@RestController
public class UserController {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	//@CrossOrigin //cross yapıyor cıfte lınk
	@PostMapping("/api/1.0/users")
	@ResponseStatus(HttpStatus.CREATED)
	public GenericResponse createUser(@Valid @RequestBody User user) {
		//log.info("Creating user: " + user.getUsername());
		
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
	
	
	@GetMapping("/api/1.0/users")
	public List<User> getUsers() {
	    return userRepository.findAll();
	}

	
	@PutMapping("/api/1.0/users/{userId}/role")
	public ResponseEntity<?> setUserRole(@PathVariable Long userId, @RequestBody RoleType roleType) {
	    Optional<User> selectedUser = userRepository.findById(userId);    
	    Optional<Role> selectedRole = roleRepository.findByName(roleType);
	    
	    User user = selectedUser.get();
	    Role role = selectedRole.get();
	    user.setRole(role);
	    userRepository.save(user);
	    
	    return new ResponseEntity<>(user, HttpStatus.OK);
	}


	
	
	/*
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
	 }*/

}
