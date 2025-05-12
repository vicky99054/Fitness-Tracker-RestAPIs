package com.ActivityService.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.ActivityService.model.ActivityType;



public class ActivityRequest {
	
	private String userId;
	private ActivityType type;
	private Integer duration;
	private Integer caloriesBurned;
	private LocalDateTime starTime;

	private Map<String, Object> additionalMetries;

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

	public ActivityRequest(String userId, ActivityType type, Integer duration, Integer caloriesBurned,
			LocalDateTime starTime, Map<String, Object> additionalMetries) {
		super();
		this.userId = userId;
		this.type = type;
		this.duration = duration;
		this.caloriesBurned = caloriesBurned;
		this.starTime = starTime;
		this.additionalMetries = additionalMetries;
	}

	public ActivityRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
