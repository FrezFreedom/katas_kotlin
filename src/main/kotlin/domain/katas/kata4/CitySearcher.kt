package org.katas.domain.katas.kata4

class CitySearcher(private val cityRepository: CityRepository) {

    fun search(term: String): List<String> {
        return when {
            term.length < 2 -> emptyList<String>()
            else -> cityRepository.all().filter { it.startsWith(term) }
        }
    }
}