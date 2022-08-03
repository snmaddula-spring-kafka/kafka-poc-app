package com.pochub.kafkapoc.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pochub.kafkapoc.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppKafkaProducer {

    @Value("${app.sample-topic}")
    private String sampleAppTopic;

    private ObjectMapper mapper = new ObjectMapper() {{
        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }};

    private final KafkaTemplate<String, Object> kafkaProducerTemplate;

    public void send(Product product) {
        try {
            ListenableFuture<SendResult<String, Object>> futureResult = kafkaProducerTemplate.send(sampleAppTopic, product);
            SendResult<String, Object> result = futureResult.get();
            log.info("Message published to kafka. OFFSET={}", sampleAppTopic, result.getRecordMetadata().offset());
        }catch(Exception ex) {
            log.info("Failed to publish message to kafka. topic={}, message={}", sampleAppTopic, product, ex);
        }
    }

    public void sendAsync(Product product) {
        ListenableFuture<SendResult<String, Object>> futureResult = kafkaProducerTemplate.send(sampleAppTopic, product);
        futureResult.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("Failed to publish message to kafka. topic={}, message={}", sampleAppTopic, product, ex);
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("Message published to kafka. OFFSET={}", sampleAppTopic, result.getRecordMetadata().offset());
            }
        });
    }
}
