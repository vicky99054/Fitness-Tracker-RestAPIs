package com.fitness.aiService.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitness.aiService.model.Recommendation;

@Repository
public interface RecommendationRepo extends JpaRepository<Recommendation, String> {

	  // Custom query method to find activities by userId
    List<Recommendation> findByUserId(String userId);

    // Custom query method to find activities by activityId
    Optional<Recommendation> findByActivityId(String activityId);
}
	

