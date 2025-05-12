package com.fitness.aiService.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitness.aiService.Repo.RecommendationRepo;
import com.fitness.aiService.model.Recommendation;

@Service
public class RecommendationService {
	
	@Autowired
	private RecommendationRepo repo;
	
	public Recommendation saveRecommendation(Recommendation recommendation) {
		
		System.out.println("Recommendation Data is Save in DataBase");
		return repo.save(recommendation);
		
	}

	public List<Recommendation> getUserRecommendation(String userId) {
		// TODO Auto-generated method stub
		return repo.findByUserId(userId);
	}
	
	public Recommendation getActivityRecommendation(String activityId) {
		// TODO Auto-generated method stub
		return repo.findByActivityId(activityId).orElseThrow(()->new RuntimeException("Not Found !"));
	}
	
	

}
