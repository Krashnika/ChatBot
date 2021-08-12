package com.example.chatbot.utils

import java.util.concurrent.atomic.AtomicInteger

object RandomInUtil {
    private val seed=AtomicInteger()

    fun getRandomInt() = seed.getAndIncrement() + System.currentTimeMillis().toInt()
}