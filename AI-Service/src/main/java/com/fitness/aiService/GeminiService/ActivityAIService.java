package com.fitness.aiService.GeminiService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitness.aiService.model.Activity;
import com.fitness.aiService.model.Recommendation;

@Service
public class ActivityAIService {
	
	@Autowired
	private GoogleGemini gemini;
	
	public Recommendation GenerateRecommendation(Activity activity) {
		
		String prompt= createPromptForActivity(activity);
		
		String aiResponse= gemini.GetAnswer(prompt);
		
		System.out.println("RESPONSE FROM AI :- "+aiResponse);
		
		 return processAiResponse(activity, aiResponse);
		
	
	}

	
	// convert jeson recommendation into String formate using this method
	
	public Recommendation processAiResponse(Activity activity , String aiResponse) {
		
		try {
			ObjectMapper objectMapper= new ObjectMapper();
			
			JsonNode rootNode=objectMapper.readTree(aiResponse);
			
			JsonNode textNode= rootNode.path("candidates")
					.get(0)
					.path("content")
					.path("parts")
					.get(0)
					.path("text");
			
			String jsonContent = textNode.asText()
					.replaceAll("```json\\n", "")
			        .replaceAll("\\n```", "")
			        .trim();
			
			System.out.println("Passed Response AI :- "+jsonContent); // print String formate Ai Response
			
			JsonNode analysisJson=objectMapper.readTree(jsonContent);
			JsonNode analysisNode= analysisJson.path("analysis");
					StringBuilder fullAnalysis= new StringBuilder();
					
					//call method AddAnylisis Section for Do saperate Text inside analysis
					
					addAnalysisSection( fullAnalysis,analysisNode,"overall", "Overall");
					addAnalysisSection( fullAnalysis,analysisNode,"pace", "Pace");
					addAnalysisSection( fullAnalysis,analysisNode,"heartrate", "Heart Rate");
					addAnalysisSection( fullAnalysis,analysisNode,"caloriesBurned", "Calories");
					
					// call next method for Improvements
					
					List<String>improvements= extractImprovements(analysisJson.path("improvements"));
					
					// call next method for Improvements
					List<String>suggestion= extractSuggestions(analysisJson.path("suggestions"));
					List<String>safety= extractSafety(analysisJson.path("safety"));
					
					Recommendation recommendation=new Recommendation();
					
					recommendation.setId(UUID.randomUUID().toString());
					recommendation.setActivityId(activity.getId());
					recommendation.setUserId(activity.getUserId());
					recommendation.setActivityType(activity.getType());
					recommendation.setRecommendation( fullAnalysis.toString().trim());
					recommendation.setImprovements(improvements);
					recommendation.setSuggestions(suggestion);
					recommendation.setSafety(safety);
					recommendation.setCreatedAt(LocalDateTime.now());
					
					return recommendation;
			
		} catch (Exception e) {
			//  handle exception
			e.printStackTrace();
			return CreateDefultRecommendation(activity);
		}
		
	}
	
	// this method return Defult Recommendation 
	private Recommendation CreateDefultRecommendation(Activity activity) {
		
		Recommendation recommendation=new Recommendation();
		
		recommendation.setId(UUID.randomUUID().toString());
		recommendation.setActivityId(activity.getId());
		recommendation.setUserId(activity.getUserId());
		recommendation.setActivityType(activity.getType());
		recommendation.setRecommendation("Unable to generate detailed analysis");
		recommendation.setImprovements(Collections.singletonList("Continue with your current routing"));
		recommendation.setSuggestions(Collections.singletonList("Consider consulting a fitness professional"));
		recommendation.setSafety(Arrays.asList(
				
				"Always warm up before exercise",
				"Stay hydrated",
				"Listen to your body"
				));
		recommendation.setCreatedAt(LocalDateTime.now());
		
		return recommendation;
	}


	//Convert 3rd Json part (Suggestions) into String   which is come from gemini ai rtesponse
	private List<String> extractSafety(JsonNode safetyNode) {
		
	List<String> safety= new ArrayList<>();
		
		if (safetyNode.isArray()) {
			
			safetyNode.forEach(item -> safety.add(item.asText()) );	
				
		}
		return safety.isEmpty() ? Collections.singletonList("Follow general safety Guidelines ") : safety;
	}
	


	//Convert 3rd Json part (Suggestions) into String   which is come from gemini ai rtesponse
	
	private List<String> extractSuggestions(JsonNode suggestionNode) {


		List<String> suggestions= new ArrayList<>();
		
		if (suggestionNode.isArray()) {
			
			suggestionNode.forEach(suggest ->{
				
				String workout= suggest.path("workout").asText();
				
				String description=suggest.path("description").asText();
				
				suggestions.add(String.format("$s: %s",workout,description));
				
			});
				
		}
		 return suggestions.isEmpty() ? Collections.singletonList("No Specific Suggestions provided") : suggestions;
	}


	//Convert Second Json part (Improvements) into String   which is come from gemini ai rtesponse
	private List<String> extractImprovements(JsonNode improvementsNode) {
		
		List<String> improvements= new ArrayList<>();
		
		if (improvementsNode.isArray()) {
			
			improvementsNode.forEach(improve ->{
				
				String area= improve.path("area").asText();
				
				String detail=improve.path("recommendation").asText();
				
				improvements.add(String.format("$s: %s",area,detail));
				
			});
				
		}
		       // Using Terenry Operator
		return improvements.isEmpty() ? Collections.singletonList("No Specific improvements provided") :improvements;
		
	}


	//Convert 1st Json part (Analysis section) into String   which is come from gemini ai rtesponse
	private void addAnalysisSection(StringBuilder stringBuilder, JsonNode analysisNode, String key, String prefix) {
		
		if (! analysisNode.path(key).isMissingNode()) {
			
			stringBuilder.append(prefix)
			.append(analysisNode.path(key).asText())
			.append("\n\n");
			
		}
		
	}


	// Create Prompt method for giving a prompt json formate
	
	private String createPromptForActivity(Activity activity) {
		
		
		
		return String.format(""" 
				
				Analyze this fitness activity and provide detailed recommendation in the following EXACT JSON formate :
				{
				 "analysis":{
				      
				      "overall": "Overall analysis here",
				      "pace": " pace analysis here",
				      "heartRate": "Heart rate analysis here",
				      "caloriesBurned": "Calories analysis here"
				 },
				    "improvements": [
				      {  
				        "area": "Area name",
				        "recommendation": "Detailed recommendation"
				      }
				    ],
				       "suggestions": [
				       {
				         "workout": "Workout name",
				         "description": " Detailed workout description"
				       }
				     ],
				        "safety": [
				        
				          "Safety point 1",
				          "Safety point 2"
				        
				     ]    
				}
				
				Analyze this activity:
				 Activity Type: %s
				 Duration: %d minutes
				 Calories Burned : %d
				 Additional Metrics: %s
				
				
				Provide detailed analysis focusing on performance , improvements, next workout suggestions, and safety guidelines.
				Ensure the response follows the EXAT JSON format show above.
				""",
				activity.getType(),
				activity.getDuration(),
				activity.getCaloriesBurned(),
				activity.getAdditionalMetries()
				
				);
	}

}
