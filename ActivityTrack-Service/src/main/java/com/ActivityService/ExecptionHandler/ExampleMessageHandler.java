package com.ActivityService.ExecptionHandler;

import org.springframework.stereotype.Component;

@Component
public class ExampleMessageHandler {
	
	 public void handleMessage(String message) {
	        System.out.println("Received message: " + message);
	    }

}
