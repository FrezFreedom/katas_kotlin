package domain.katas.kata4

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.katas.domain.katas.kata4.CityRepository
import org.katas.domain.katas.kata4.Database

class CityRepositoryTest {

    @DisplayName("all should return a list of all cities")
    @Test
    fun testAll(){
        val expectedValue = listOf("Paris", "Budapest", "Skopje", "Rotterdam", "Valencia",
            "Vancouver", "Amsterdam", "Vienna", "Sydney", "New York City",
            "London", "Bangkok", "Hong Kong", "Dubai", "Rome", "Istanbul")
        val database = Database()
        val cityRepository = CityRepository(database)

        val result = cityRepository.all()

        Assertions.assertEquals(expectedValue, result)
    }

    @DisplayName("searchByTerm should return a list of cities that start with specific term")
    @ParameterizedTest
    @MethodSource("searchByTermData")
    fun testSearchByTerm(expectedValue: List<String>, term: String){
        val database = Database()
        val cityRepository = CityRepository(database)

        val result = cityRepository.findCitiesStartingWith(term)

        Assertions.assertEquals(expectedValue, result)
    }

    companion object {
        @JvmStatic
        fun searchByTermData() = listOf(
            Arguments.of(emptyList<String>(), "x"),
            Arguments.of(listOf("Valencia", "Vancouver"), "Va"),
            Arguments.of(emptyList<String>(), "Xa"),
            Arguments.of(listOf("Paris", "Budapest", "Skopje", "Rotterdam", "Valencia",
                "Vancouver", "Amsterdam", "Vienna", "Sydney", "New York City",
                "London", "Bangkok", "Hong Kong", "Dubai", "Rome", "Istanbul"), ""),
            Arguments.of(listOf("Istanbul"), "Is"),
            Arguments.of(listOf("Valencia", "Vancouver"), "va"),
        )
    }
}