package com.example.kafka.service

import org.springframework.stereotype.Component

@Component
class CounterService {
    private var counter: Int = 0

    fun inc() {
        counter++
    }

    fun result() : Int {
        return counter
    }
}