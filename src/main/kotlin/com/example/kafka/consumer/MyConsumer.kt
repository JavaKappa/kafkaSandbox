package com.example.kafka.consumer

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.time.Duration
import java.util.*
import java.util.function.Consumer
import kotlin.concurrent.thread

class MyConsumer(topic: String) : AutoCloseable{
    private val consumer: KafkaConsumer<String, String>
    private val topic: String

    init {
        this.topic = topic
        val props = Properties()

        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        props[ConsumerConfig.GROUP_ID_CONFIG] = "asda"
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringDeserializer"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringDeserializer"

        consumer = KafkaConsumer<String, String>(props)
        consumer.subscribe(listOf(topic))
    }

    fun consume(recordConsumer: Consumer<ConsumerRecord<String, String>>) {
        thread(isDaemon = true) {
            while (true) {

                val poll = consumer.poll(Duration.ofSeconds(1))
                println(poll.count())

                poll.forEach {
                    recordConsumer.accept(it)
                }
            }
        }
    }

    override fun close() = consumer.close()

}