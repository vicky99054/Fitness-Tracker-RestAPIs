package com.fitness.aiService.model;

import java.time.LocalDateTime;
import java.util.Map;

public class Activity {
	
	private String id;
	private String userId;
	private Integer duration;
	private Integer caloriesBurned;
	private String type;
	
	private LocalDateTime starTime; 
    private Map<String, Object> additionalMetries;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	// Getter And Setter
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getCaloriesBurned() {
		return caloriesBurned;
	}
	public void setCaloriesBurned(Integer caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}
	public LocalDateTime getStarTime() {
		return starTime;
	}
	public void setStarTime(LocalDateTime starTime) {
		this.starTime = starTime;
	}
	public Map<String, Object> getAdditionalMetries() {
		return additionalMetries;
	}
	public void setAdditionalMetries(Map<String, Object> additionalMetries) {
		this.additionalMetries = additionalMetries;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Activity(String id, String userId, Integer duration, Integer caloriesBurned, LocalDateTime starTime,
			Map<String, Object> additionalMetries, LocalDateTime createdAt, LocalDateTime updatedAt , String type) {
		super();
		this.id = id;
		this.userId = userId;
		this.duration = duration;
		this.caloriesBurned = caloriesBurned;
		this.starTime = starTime;
		this.additionalMetries = additionalMetries;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.type=type;
	}
	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
