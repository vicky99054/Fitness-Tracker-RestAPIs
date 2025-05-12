package com.ActivityService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ActivityService.Service.ActivityService;
import com.ActivityService.UserValidation.UserValidationService;
import com.ActivityService.dto.ActivityRequest;
import com.ActivityService.dto.ActivityResponce;


@RestController
@RequestMapping("/api/activity")
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private UserValidationService userValidationService;
	
	
	@PostMapping("/save")
	public ResponseEntity<ActivityResponce>trackActivity(@RequestBody ActivityRequest request){
		return ResponseEntity.ok(activityService.trackActivity(request));
	}
	
	@GetMapping("/{Uid}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ActivityResponce> getUserActivityResponce(@PathVariable String Uid) {
		return activityService.GetUserActvity(Uid);
	}
	
	@GetMapping("valid/{userId}")
	public boolean validUser( @PathVariable String userId) {
		boolean isvalid = userValidationService.validateUser( userId);
		System.out.println(isvalid +" "+ userId);
		
		if (isvalid) {
			
			return true;
		} else {

			return false;
		}
		
	}

}
