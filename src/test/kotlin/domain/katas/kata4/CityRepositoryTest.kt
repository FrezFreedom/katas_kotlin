package domain.katas.kata4

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
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
}