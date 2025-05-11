package com.userService.service;

import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userService.Model.UserModel;
import com.userService.Repo.UserRepo;
import com.userService.dto.RegisterRequest;
import com.userService.dto.UserResponce;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	 
	@Autowired
	private UserRepo repo;
	
	// Save User in Db
	public UserResponce RegisterUser(RegisterRequest registerRequest) {
		
		 String userid =UUID.randomUUID().toString();
		
		if (repo.existsByEmail(registerRequest.getEmail())) {
			throw new RuntimeException("Email Already Exist");
		}
		
		UserModel userModel= new UserModel();
		userModel.setId(userid);
		userModel.setEmail(registerRequest.getEmail());
		userModel.setPassword(registerRequest.getPassword());
		userModel.setFirstName(registerRequest.getFirstName());
		userModel.setLastName(registerRequest.getLastName());
		
		//Save Entity in database
	    UserModel saveUser=repo.save(userModel);
		
		UserResponce userResponce = new UserResponce();
		
		userResponce.setId(saveUser.getId());
		userResponce.setEmail(saveUser.getEmail());
		userResponce.setPassword(saveUser.getPassword());
		userResponce.setFirstName(saveUser.getFirstName());
		userResponce.setLastName(saveUser.getLastName());
		userResponce.setCreatedTa(saveUser.getCreatedTa());
		userResponce.setUpdatedAt(saveUser.getUpdatedAt());
		
		return userResponce;
	}
	
	// Get Single User profile
	
	public UserResponce GetUserProfile(String UserId) {
		
		// Fetch data from database 
		UserModel user= repo.findById(UserId)
				.orElseThrow(()-> new RuntimeException("User not Exist"));
		
        UserResponce userResponce = new UserResponce();
		
		userResponce.setId(user.getId());
		userResponce.setEmail(user.getEmail());
		userResponce.setPassword(user.getPassword());
		userResponce.setFirstName(user.getFirstName());
		userResponce.setLastName(user.getLastName());
		userResponce.setCreatedTa(user.getCreatedTa());
		userResponce.setUpdatedAt(user.getUpdatedAt());
		
		return userResponce;
	}

	
	// this service is use to validate user 
	//if user are prasent in db it return TRUE else FALSE
	
	public boolean existById(String userId) {
		
		return repo.existsById(userId);
	}

}
