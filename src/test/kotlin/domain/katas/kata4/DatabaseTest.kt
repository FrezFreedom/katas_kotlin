package domain.katas.kata4

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.katas.domain.katas.kata4.Database

class DatabaseTest {

    @Test
    fun testGetAllCities()
    {
        val database = Database()
        val expectedValue = listOf("Paris", "Budapest", "Skopje", "Rotterdam", "Valencia",
            "Vancouver", "Amsterdam", "Vienna", "Sydney", "New York City",
            "London", "Bangkok", "Hong Kong", "Dubai", "Rome", "Istanbul")

        val allCities = database.getAllCities()

        Assertions.assertEquals(expectedValue, allCities)
    }
}