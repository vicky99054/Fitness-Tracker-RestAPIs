package com.userService.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USER")
@Data
public class UserModel {
	
	@Id
	
	private String id;
	
	@Column(unique = true , nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	private String firstName;
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private UserRole role=UserRole.USER;
	
	@CreationTimestamp
	private LocalDateTime createdTa;
	@UpdateTimestamp
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
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
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
	public UserModel(String id, String email, String password, String firstName, String lastName, UserRole role,
			LocalDateTime createdTa, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.createdTa = createdTa;
		this.updatedAt = updatedAt;
	}
	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
