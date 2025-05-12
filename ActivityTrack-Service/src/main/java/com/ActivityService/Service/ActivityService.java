package com.ActivityService.Service;

import java.util.List;
import com.ActivityService.dto.ActivityRequest;
import com.ActivityService.dto.ActivityResponce;
import java.util.UUID;
import java.util.stream.Collectors;
import com.ActivityService.model.ActivityModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ActivityService.Repo.ActivityRepo;
import com.ActivityService.UserValidation.UserValidationService;

@Service
public class ActivityService {
	
	@Value("${spring.rabbitmq.template.exchange}")
	private String exchange;
	
	@Value("${spring.rabbitmq.template.routing-key}")
	private String routingKey;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	private RabbitTemplate rabbitTemplate;
	
	public ActivityService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate=rabbitTemplate;
	}
	
	@Autowired
	private ActivityRepo repo;
	
	@Autowired
	private UserValidationService userValidationService;
	
public ActivityResponce trackActivity(ActivityRequest request) {
		
		boolean isUservalid=userValidationService.validateUser(request.getUserId());
		
	if (! isUservalid) {
			throw new RuntimeException("Invalid user: "+ request.getUserId());
		}
		
		com.ActivityService.model.ActivityModel model= new ActivityModel();
		
		model.setId(UUID.randomUUID().toString());
		model.setUserId(request.getUserId());
		model.setDuration(request.getDuration());
		model.setType(request.getType());
		model.setCaloriesBurned(request.getCaloriesBurned());
		model.setStarTime(request.getStarTime());
		model.setAdditionalMetries(request.getAdditionalMetries());
		
		
		 ActivityModel model2= repo.save(model);
		 
		 //Publish to RabbitMQ for AI Processing
		 
		 try {
			  
			  String jsonmessage= objectMapper.writeValueAsString(model);
			  
	            rabbitTemplate.convertAndSend(exchange, routingKey, jsonmessage);
	            System.out.println("User activity message sent to RabbitMQ.");
	        } catch (Exception e) {
	            System.err.println("Exception in RabbitMQ communication: " + e.getMessage());
	        }
		 
		
		
		return mapToResponce(model2); // this is responce method
	}
	
	//Get Single user Activity


    
	
	public List<ActivityResponce> GetUserActvity(String uId) {
		List<ActivityModel> model= repo.findByUserId(uId);
		
		
		
		 
	
//		 rabbitTemplate.convertAndSend("fitnessActivity.queue", model);
		
		return model.stream().map(this::mapToResponce).collect(Collectors.toList());
	}
	
	// this method is use for Responce data
	
	private ActivityResponce mapToResponce(ActivityModel model) {
		
		ActivityResponce responce= new ActivityResponce();
		
		responce.setId(model.getId());
		responce.setUserId(model.getUserId());
		responce.setType(model.getType());
		responce.setCaloriesBurned(model.getCaloriesBurned());
		responce.setDuration(model.getDuration());
		responce.setStarTime(model.getStarTime());
		responce.setAdditionalMetries(model.getAdditionalMetries());
		responce.setCreatedAt(model.getCreatedAt());
		responce.setUpdatedAt(model.getUpdatedAt());
		
		return responce;
	}
}
