package com.fitness.aiService.RabbitReciveMessage;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitness.aiService.GeminiService.ActivityAIService;
import com.fitness.aiService.Repo.RecommendationRepo;
import com.fitness.aiService.model.Activity;
import com.fitness.aiService.model.Recommendation;

@Service

public class MessageListnierRabbit {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ActivityAIService activityAIService;
	
	@Autowired
	private RecommendationRepo recommendationRepo;
	
	@RabbitListener(queues = "fitnessActivity.queue")
	public void ReciveMessage(@Payload  String message) {
		
		try {
			Activity activity=objectMapper.readValue(message, Activity.class);
			
			System.out.println(activity.getId());
			System.out.println(activity.getUserId());
			System.out.println("___________________________________");
			
			System.out.println(activity);
			
			Recommendation recommendation=
			recommendationRepo.save(activityAIService.GenerateRecommendation(activity));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		//  AIzaSyA7yJpRN2zL1j5fxcycxdjmxLI7OSc_Z-o
		
	}

	@RabbitListener(queues = "newQueue")
	public void ReciveString( @Payload String message) {
		
		System.out.println(message);
		
		System.out.println("------------------------------------------\n--------------------------------------");
	}
}
