package com.project.nva;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaListenner{
	
	@KafkaListener(topics = "demo", groupId = "group-id")
    public void listen(String message) {
        System.out.println("Received Message in group - group-id: " + message);
    }
}
	