package domain.katas.kata4

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.katas.domain.katas.kata4.CityRepository
import org.katas.domain.katas.kata4.Database
import kotlin.test.assertTrue

class CityRepositoryTest {
    private val mockDatabase = mockk<Database> {
        every { getAllCities() } answers { listOf("Paris", "Vancouver", "Valencia") }
    }


    @DisplayName("validate that all function of CityRepository return all cities correctly")
    @Test
    fun testAll(){
        val expectedValue = listOf("Paris", "Vancouver", "Valencia")
        val cityRepository = CityRepository(mockDatabase)

        val result = cityRepository.all()

        Assertions.assertEquals(expectedValue.size, result.size)
        assertTrue { expectedValue.containsAll(result) }
        assertTrue { result.containsAll(expectedValue) }
    }

    @DisplayName("validate that searchByTerm function in CityRepository can return cities that contain the specific term")
    @ParameterizedTest
    @MethodSource("searchByTermData")
    fun testSearchByTerm(expectedValue: List<String>, term: String){
        val cityRepository = CityRepository(mockDatabase)

        val result = cityRepository.searchByTerm(term)

        Assertions.assertEquals(expectedValue.size, result.size)
        assertTrue { expectedValue.containsAll(result) }
        assertTrue { result.containsAll(expectedValue) }
    }

    companion object {
        @JvmStatic
        fun searchByTermData() = listOf(
            Arguments.of(emptyList<String>(), "x"),
            Arguments.of(listOf("Valencia", "Vancouver"), "Va"),
            Arguments.of(listOf("Valencia", "Vancouver"), "va"),
            Arguments.of(listOf("Paris", "Vancouver", "Valencia"), ""),
            Arguments.of(listOf("Vancouver"), "eR"),
        )
    }
}