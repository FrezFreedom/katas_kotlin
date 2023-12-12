package domain.katas.kata4

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.katas.domain.katas.kata4.CitySearcher

class CitySearcherTest {

    @DisplayName("search should return empty list for short term")
    @Test
    fun testSearch(){
        val expectedValue = emptyList<String>()
        val citySearcher = CitySearcher()

        val searchResult = citySearcher.search("x")

        Assertions.assertEquals(expectedValue, searchResult)
    }
}