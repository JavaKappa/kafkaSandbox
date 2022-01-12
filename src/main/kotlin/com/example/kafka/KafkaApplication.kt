package com.example.kafka

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableAdminServer
class KafkaApplication

fun main(args: Array<String>) {
	runApplication<KafkaApplication>(*args)
}
