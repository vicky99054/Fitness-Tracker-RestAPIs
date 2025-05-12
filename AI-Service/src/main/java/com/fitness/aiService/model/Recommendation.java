package com.fitness.aiService.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "Recommendation_Model")
public class Recommendation {
	
	 @Id
	private String Id;
	 
	private String activityId;
	private String userId;
	private String activityType;
	
     @Column(length = 2000)
	private String recommendation;
	
	@ElementCollection
	private List<String> improvements;
	@ElementCollection
	private List<String>suggestions;
	@ElementCollection
	private List<String>safety;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	// gatter and Setter

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public List<String> getImprovements() {
		return improvements;
	}

	public void setImprovements(List<String> improvements) {
		this.improvements = improvements;
	}

	public List<String> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<String> suggestions) {
		this.suggestions = suggestions;
	}

	public List<String> getSafety() {
		return safety;
	}

	public void setSafety(List<String> safety) {
		this.safety = safety;
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

	public Recommendation(String id, String activityId, String userId, String activityType, String recommendation,
			List<String> improvements, List<String> suggestions, List<String> safety, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		Id = id;
		this.activityId = activityId;
		this.userId = userId;
		this.activityType = activityType;
		this.recommendation = recommendation;
		this.improvements = improvements;
		this.suggestions = suggestions;
		this.safety = safety;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Recommendation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
	
	

}
