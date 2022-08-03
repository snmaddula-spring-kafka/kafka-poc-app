package com.pochub.kafkapoc.controller;

import com.pochub.kafkapoc.domain.Product;
import com.pochub.kafkapoc.service.AppKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AppSampleController {

    private final AppKafkaProducer appKafkaProducer;

    @PostMapping("/publish")
    public void publishMessage(@RequestBody Product product) {
        appKafkaProducer.send(product);
    }

}
