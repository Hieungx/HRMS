package com.project.nva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaListenerApplication.class, args);
	}

}
