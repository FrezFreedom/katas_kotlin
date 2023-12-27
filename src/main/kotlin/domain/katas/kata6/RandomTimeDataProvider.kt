package org.katas.domain.katas.kata6

import com.google.inject.Singleton
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Singleton
class RandomTimeDataProvider: DateProvider {
    override fun now(): String {
        val currentTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        return currentTime.format(formatter)
    }
}