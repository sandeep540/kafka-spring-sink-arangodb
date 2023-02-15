package com.plural.kafkagen;

import com.github.javafaker.Faker;
import com.plural.kafkasink.model.Product;
import io.confluent.kafka.serializers.KafkaJsonSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;


import java.util.Properties;
import java.util.UUID;

public class KafkaGen {

    private static final String TOPIC = "product_json";
    private static final String BOOTSTRAP_SERVERS = "localhost:19092";

    public static void main(String[] args) {

        Properties kafkaProps = new Properties();
        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSerializer.class.getName());

                try (Producer<String, Product> producer = new KafkaProducer<>(kafkaProps)) {
                    // here, we run an infinite loop to send a message to the cluster every second
                    for (int i = 0;; i++) {
                        String key = String.valueOf(UUID.randomUUID());
                        Product message = generateFakeData();

                        producer.send(new ProducerRecord<>(TOPIC, key, message));

                        // log a confirmation once the message is written
                        System.out.println("sent msg " + key + " : " + message);
                        try {
                            // Sleep for a second
                            Thread.sleep(250);
                        } catch (Exception e) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Could not start producer: " + e);
                }
    }

    private static Product generateFakeData() {
        Faker faker = new Faker();

        Product prod = new Product(String.valueOf(faker.number().numberBetween(1000, 9999)), faker.name().name(), faker.company().name() );
        return prod;
    }


}



