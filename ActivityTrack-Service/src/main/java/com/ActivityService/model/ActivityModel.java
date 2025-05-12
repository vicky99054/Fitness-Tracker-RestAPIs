package com.ActivityService.model;



import java.time.LocalDateTime;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "Activity")

public class ActivityModel {
	
	@Id
	private String id;
	private String userId;
	private ActivityType type;
	private Integer duration;
	private Integer caloriesBurned;
	private LocalDateTime starTime;
	
	     @Convert(converter = com.ActivityService.JsonConverter.MapToJsonConverter.class )
	     @Column(columnDefinition = "TEXT")
	    private Map<String, Object> additionalMetries;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;
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
	public ActivityType getType() {
		return type;
	}
	public void setType(ActivityType type) {
		this.type = type;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
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
	public ActivityModel(String id, String userId, ActivityType type, Integer duration, Integer caloriesBurned,
			LocalDateTime starTime, Map<String, Object> additionalMetries, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.type = type;
		this.duration = duration;
		this.caloriesBurned = caloriesBurned;
		this.starTime = starTime;
		this.additionalMetries = additionalMetries;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public ActivityModel() { 
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
