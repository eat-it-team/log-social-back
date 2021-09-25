/*
package ru.eatit.integration.service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UpdateFieldKafkaListener {

    @KafkaListener(topics = "${integration_service.kafka.topicName}", id = "${integration_service.kafka.groupId}" )
    public void listener(ConsumerRecord<String, String> record) {
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.topic());
        System.out.println(record.offset());
    }
}
*/
