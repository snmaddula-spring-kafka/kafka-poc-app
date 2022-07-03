package com.pochub.kafkapoc.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaTopicsConfig {

    @Value("${app.sample-topic}")
    private String sampleAppTopic;

    private final KafkaAdmin kafkaAdmin;

    @PostConstruct
    public void createOrModifyTopics() {
        log.info("Creating Sample Topic: {}", sampleAppTopic);
        NewTopic sampleTopic = TopicBuilder.name(sampleAppTopic).partitions(1).replicas(1).config(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(3600000 * 24)).build();
        kafkaAdmin.createOrModifyTopics(sampleTopic);
        log.info("Created Sample Topic:: {}", sampleTopic);
    }

}
