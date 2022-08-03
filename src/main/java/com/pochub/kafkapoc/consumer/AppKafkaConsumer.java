package com.pochub.kafkapoc.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppKafkaConsumer {

    @KafkaListener(topics = "${app.sample-topic}", groupId="sampleConsumerGroup",  containerFactory = "sampleListenerContainerFactory")
    public void onMessage(ConsumerRecord message) {
        log.info("Message Received: {}", message);
    }
}
