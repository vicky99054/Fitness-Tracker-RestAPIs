package com.fitness.aiService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.aiService.Service.RecommendationService;
import com.fitness.aiService.model.Recommendation;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {
	
	@Autowired
	private RecommendationService recommendationService;
	
	// Save Data in database
	
	@PostMapping("/save")
	public ResponseEntity<Recommendation>SaveRecommendation(@RequestBody Recommendation recommendation){
		
		return ResponseEntity.ok(recommendationService.saveRecommendation(recommendation));
	}
	
	
	// Get Data using userId 
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Recommendation>>getUserRecommendatoion(@PathVariable String userId){
		System.out.println(userId);
		return ResponseEntity.ok(recommendationService.getUserRecommendation(userId));
	}
	
	// Get Data using activityId
	
	@GetMapping("/activity/{activityId}")
	public ResponseEntity<Recommendation>getActivityRecommendation(@PathVariable String activityId){
		
		System.out.println(activityId);
		return ResponseEntity.ok(recommendationService.getActivityRecommendation(activityId));
	}

}
