package com.ActivityService.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.ActivityService.ExecptionHandler.ExampleMessageHandler;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.FatalExceptionStrategy;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class WebClientConfig {
	
	@Value("${spring.rabbitmq.template.exchange}")
	private String exchange;
	
	@Value("${spring.rabbitmq.template.routing-key}")
	private String routingKey;
	
//	@Value("${spring.rabbitmq.template.default-receive-queue}")
//	private String queue;
//	
	
	
	
	@Bean
	@LoadBalanced
	public WebClient.Builder webClientBuilder(){
		return WebClient.builder();
	}
	
	@Bean
	public WebClient userServiceWebclient(WebClient.Builder webClientBuilder) {
		return webClientBuilder.baseUrl("http://USER-SERVICE")
				.build();
	}
	
	
	// RabbitMQ configuration Template
	
	

    @Bean
    public Queue queue() {
        return new Queue("fitnessActivity.queue", true); 
        // This method is return bean of queue
    }

//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setMessageConverter(jsonMessageConverter());
//        return template;
//    }
//
//    @Bean
//    public Jackson2JsonMessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//                                                    MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames("fitnessActivity.queue");
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }
//
//    @Bean
//    public MessageListenerAdapter listenerAdapter(ExampleMessageHandler handler) {
//        return new MessageListenerAdapter(handler, "handleMessage");
//    }
//    
//    @Bean
//    public DirectExchange directExchange() {
//    	return new DirectExchange(exchange);
//    }
//    
//    @Bean
//    public Binding activityBinding(Queue queue,DirectExchange directExchange) {
//    	return BindingBuilder.bind(queue).to(directExchange).with(routingKey);
//    }
    
   

}


