package com.example.kafka.config

import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaConfig {
    companion object {
        const val TOPIC = "spring-kafka-demo"
    }

    fun producerFactory(kafkaProperties: KafkaProperties) : ProducerFactory<String, String> {
        return DefaultKafkaProducerFactory(kafkaProperties.buildProducerProperties())
    }

    fun kafkaTemplate(factory: ProducerFactory<String, String>) : KafkaTemplate<String, String> {
        return KafkaTemplate(factory)
    }
}