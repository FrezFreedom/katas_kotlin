package domain.katas.kata3.validator

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.katas.domain.katas.kata3.dto.PasswordValidationResult
import org.katas.domain.katas.kata3.validator.NumericValidator

class NumericValidatorTest {
    private val numericValidator = NumericValidator()

    @ParameterizedTest
    @MethodSource("numericValidatorData")
    fun testNumericValidator(expectedValue: PasswordValidationResult, input: String){

        val result = numericValidator.validate(input)

        Assertions.assertEquals(expectedValue, result)
    }

    companion object {
        @JvmStatic
        fun numericValidatorData() = listOf(
            Arguments.of(
                PasswordValidationResult(
                    true,
                    mutableSetOf()
                ), "ABCDEF78"),
            Arguments.of(
                PasswordValidationResult(
                    false,
                    mutableSetOf("The password must contain at least 2 numbers")
                ), "ABCDEFG8"),
            Arguments.of(
                PasswordValidationResult(
                    false,
                    mutableSetOf("The password must contain at least 2 numbers")
                ), ""),
        )
    }
}