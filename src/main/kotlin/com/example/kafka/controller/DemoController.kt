package com.example.kafka.controller

import com.example.kafka.config.KafkaConfig
import com.example.kafka.service.CounterService
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class DemoController(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val counterService: CounterService
) {

    @GetMapping("/send-message")
    fun sendMessage(): String {
        val send = kafkaTemplate.send(KafkaConfig.TOPIC, UUID.randomUUID().toString(), UUID.randomUUID().toString())
        val get = send.get()
        println(get)
        return "OK"
    }

    @GetMapping("/result")
    fun showResult(): Int {
        return counterService.result()
    }
}