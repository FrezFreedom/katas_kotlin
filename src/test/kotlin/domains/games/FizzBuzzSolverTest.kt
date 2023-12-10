package domains.games

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class FizzBuzzSolverTest {

    // Arrange
    private val fizzBuzzSample: FizzBuzzSolver = FizzBuzzSolver()

    @ParameterizedTest
    @MethodSource("fizzBuzzData")
    fun testFizzBuzzV3(expectedValue: String, input: Int){
        // Act
        val result = fizzBuzzSample.solve(input)
        // Assert
        Assertions.assertEquals(expectedValue, result)
    }

    companion object {
        @JvmStatic
        fun fizzBuzzData() = listOf(
            Arguments.of("Fizz", 3),
            Arguments.of("Fizz", 6),
            Arguments.of("Buzz", 5),
            Arguments.of("Buzz", 10),
            Arguments.of("7", 7),
            Arguments.of("FizzBuzz", 15),
            Arguments.of("FizzBuzz", 60),
        )
    }
}