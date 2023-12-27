package org.katas.domain.katas.kata4

import com.google.inject.Singleton

@Singleton
class Database {
    companion object {
        private val cities = listOf("Paris", "Budapest", "Skopje", "Rotterdam", "Valencia",
            "Vancouver", "Amsterdam", "Vienna", "Sydney", "New York City",
            "London", "Bangkok", "Hong Kong", "Dubai", "Rome", "Istanbul")
    }

    fun getAllCities() = cities

}