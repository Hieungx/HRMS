package com.spring.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/kafka")
public class KafkaProducerController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping(value = "/producer")
    public Object sendMessage(@RequestParam String msg) {
        kafkaTemplate.send("demo", msg);
        return "sent";
    }
}
