package domain.katas.kata4

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.katas.domain.katas.kata4.CityRepository
import org.katas.domain.katas.kata4.CitySearcher
import org.katas.domain.katas.kata4.Database

class CitySearcherTest {

    @DisplayName("search should return empty list for short term")
    @ParameterizedTest
    @MethodSource("searchData")
    fun testSearch(expectedValue: List<String>, term: String){
        val database = Database()
        val cityRepository = CityRepository(database)
        val citySearcher = CitySearcher(cityRepository)

        val searchResult = citySearcher.search(term)

        Assertions.assertEquals(expectedValue, searchResult)
    }

    companion object {
        @JvmStatic
        fun searchData() = listOf(
            Arguments.of(emptyList<String>(), "x"),
            Arguments.of(listOf("Valencia", "Vancouver"), "Va"),
            Arguments.of(emptyList<String>(), "Xa"),
            Arguments.of(emptyList<String>(), ""),
            Arguments.of(listOf("Istanbul"), "Is"),
        )
    }
}