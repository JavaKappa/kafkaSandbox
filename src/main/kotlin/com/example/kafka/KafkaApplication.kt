package com.example.kafka

import com.example.kafka.consumer.MyConsumer
import com.example.kafka.producer.MyProducer
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

@SpringBootApplication
class KafkaApplication

fun main(args: Array<String>) {
	val topic = "spring-kafka-demo"
	val producer = MyProducer(topic)
	val consumer = MyConsumer(topic)

	consumer.consume {
		println("Got key: ${it.key()}, value: ${it.value()} !!!")
	}

	thread(isDaemon = true) {
		var sec = 0;
		(1.. 5).forEach {
			producer.send("key", "HELLO FROM Producer SECOND ${sec++}")
			TimeUnit.SECONDS.sleep(1)
		}
	}

	TimeUnit.SECONDS.sleep(10)

	consumer.close()
	producer.close()

}
