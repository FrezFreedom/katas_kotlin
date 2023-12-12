package org.katas.domain.katas.kata4

class CityRepository(private val database: Database) {

    fun all(): List<String> {
        return database.getAllCities()
    }

    fun searchByTerm(term: String): List<String> {
        return database.getAllCities().filter { it.startsWith(term) }
    }
}