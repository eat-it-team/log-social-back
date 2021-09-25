/*
package ru.eatit.integration.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${integration_service.kafka.topicName}")
    private String topicName;

    @PostConstruct
    public void init() {
        sendMessage("custom_snils", "1234567");
    }

    public void sendMessage(String key, String value) {
        kafkaTemplate.send(topicName, key, value);
    }
}
*/
