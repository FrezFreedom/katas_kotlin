package domain.katas.kata4

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.katas.domain.katas.kata4.CityRepository
import org.katas.domain.katas.kata4.CitySearcher
import kotlin.test.assertTrue

class CitySearcherTest {
    private val mockCityRepository = mockk<CityRepository>() {
        every { searchByTerm(any()) } answers { listOf("Vancouver", "Valencia") }
        every { all() } answers { listOf("Paris", "Vancouver", "Valencia") }
    }

    @DisplayName("validate search function of CitySearcher that can return correctly cities list")
    @ParameterizedTest
    @MethodSource("searchData")
    fun testSearch(expectedValue: List<String>, term: String){
        val citySearcher = CitySearcher(mockCityRepository)

        val result = citySearcher.search(term)

        Assertions.assertEquals(expectedValue.size, result.size)
        assertTrue { expectedValue.containsAll(result) }
        assertTrue { result.containsAll(expectedValue) }
    }

    companion object {
        @JvmStatic
        fun searchData() = listOf(
            Arguments.of(emptyList<String>(), "x"),
            Arguments.of(listOf("Valencia", "Vancouver"), "Va"),
            Arguments.of(listOf("Paris", "Vancouver", "Valencia"), "*"),
        )
    }
}