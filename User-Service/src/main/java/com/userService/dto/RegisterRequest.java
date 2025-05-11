package com.userService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

	@NotBlank(message = "Email is required")
	@Email(message ="Invalid email formate" )
	private String email;
	
	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "password must have atleast 6 characters")
	private String password;
	private String firstName;
	private String lastName;
	
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public RegisterRequest(
			@NotBlank(message = "Email is required") @Email(message = "Invalid email formate") String email,
			@NotBlank(message = "Password is required") @Size(min = 6, message = "password must have atleast 6 characters") String password,
			String firstName, String lastName) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public RegisterRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
