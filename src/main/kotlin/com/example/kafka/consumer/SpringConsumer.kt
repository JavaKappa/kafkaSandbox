package com.example.kafka.consumer

import com.example.kafka.config.KafkaConfig
import com.example.kafka.service.CounterService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class SpringConsumer(
    private val incrementService: CounterService
) {

    @KafkaListener(topics = [KafkaConfig.TOPIC])
    fun inc() {
        incrementService.inc()
    }
}