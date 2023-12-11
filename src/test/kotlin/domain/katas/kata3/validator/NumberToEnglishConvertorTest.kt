package domain.katas.kata3.validator


import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.katas.domain.katas.kata3.validator.NumberToEnglishConvertor

class NumberToEnglishConvertorTest {
    private val numberToEnglishConvertor = NumberToEnglishConvertor()

    @ParameterizedTest
    @MethodSource("numericValidatorData")
    fun testConvert(expectedValue: String, input: Int){

        val result = numberToEnglishConvertor.convert(input)

        assertEquals(expectedValue, result)
    }

    companion object {
        @JvmStatic
        fun numericValidatorData() = listOf(
            Arguments.of("one", 1),
            Arguments.of("two", 2),
            Arguments.of("three", 3),
            Arguments.of("four", 4),
            Arguments.of("five", 5),
        )
    }
}