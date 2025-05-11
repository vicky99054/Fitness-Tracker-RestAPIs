package com.userService.dto;

import java.time.LocalDateTime;

import lombok.Data;


@Data
public class UserResponce {
	
    private String id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private LocalDateTime createdTa;
	private LocalDateTime updatedAt;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public LocalDateTime getCreatedTa() {
		return createdTa;
	}
	public void setCreatedTa(LocalDateTime createdTa) {
		this.createdTa = createdTa;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public UserResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserResponce(String id, String email, String password, String firstName, String lastName,
			LocalDateTime createdTa, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.createdTa = createdTa;
		this.updatedAt = updatedAt;
	}
	
	

}
