package org.katas.domain.katas.kata4

class CitySearcher(private val database: Database) {

    fun search(term: String): List<String> {
        return when {
            term.length < 2 -> emptyList<String>()
            else -> database.getAllCities().filter { it.startsWith(term) }
        }
    }
}