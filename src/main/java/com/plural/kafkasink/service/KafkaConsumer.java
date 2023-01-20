package com.plural.kafkasink.service;

import com.plural.kafkasink.model.Product;
import com.plural.kafkasink.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private ProductRepository repository;

    @KafkaListener(topics = "product",
            groupId = "group", properties = {"spring.json.value.default.type=com.plural.kafkasink.model.Product"})
    public void consume(Product message){
        LOGGER.info(String.format("Message received -> %s", message.toString()));

        com.plural.kafkasink.entity.Product prod = new com.plural.kafkasink.entity.Product(message.getId(), message.getName(), message.getBrand());
        repository.save(prod);
        LOGGER.info(String.format("Message saved -> %s", prod));
    }
}