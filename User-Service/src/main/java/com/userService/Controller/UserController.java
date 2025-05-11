package com.userService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.userService.dto.RegisterRequest;
import com.userService.dto.UserResponce;
import com.userService.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// single user profile
	
	@GetMapping("/{userId}")
	
	public ResponseEntity<?>getuserProfile(@PathVariable String userId){
		
		return ResponseEntity.ok(userService.GetUserProfile(userId));
	}
	
	// Register User
	@PostMapping("/register")
	
	public ResponseEntity<UserResponce> registerUser(@Valid @RequestBody RegisterRequest registerRequest ){
		
		UserResponce user= userService.RegisterUser(registerRequest);
	
		return ResponseEntity.ok(user);
	}
	
	
	// Validate User (TRUE OR FALSE)
	
@GetMapping("/{userId}/valid")
	
	public ResponseEntity<Boolean>ValidateUser(@PathVariable String userId){
		
		return ResponseEntity.ok(userService.existById(userId));
	}
	
	
	

}
