package com.example.kafka.producer

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.*

class MyProducer(topic: String) : AutoCloseable {
    private val producer: KafkaProducer<String, String>
    private val topic: String


    init {
        this.topic = topic;
        val props = Properties();

        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        props[ProducerConfig.CLIENT_ID_CONFIG] = "clientId"
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"
        props[ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG] = 5000

        producer =  KafkaProducer<String, String>(props)
    }

    fun send(key: String, value: String) {
        producer.send(ProducerRecord(topic, key, value)).get()
    }

    override fun close() {
        producer.close()
    }


}