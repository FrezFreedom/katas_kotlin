package org.katas.domain.katas.kata4

import com.google.inject.Inject

class CityRepository @Inject constructor(private val database: Database) {

    fun all(): List<String> {
        return database.getAllCities()
    }

    fun searchByTerm(term: String): List<String> {
        return database.getAllCities().filter { it.lowercase().contains(term.lowercase()) }
    }
}