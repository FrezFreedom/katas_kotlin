package domain.katas.kata3.validator

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.katas.domain.katas.kata3.dtos.PasswordValidationResponse
import org.katas.domain.katas.kata3.validators.LengthValidator

class LengthValidatorTest {

    private val lengthValidator = LengthValidator()

    @ParameterizedTest
    @MethodSource("lengthValidatorData")
    fun testLengthValidator(expectedValue: PasswordValidationResponse, input: String){

        val result = lengthValidator.validate(input)

        Assertions.assertEquals(expectedValue, result)
    }

    companion object {
        @JvmStatic
        fun lengthValidatorData() = listOf(
            Arguments.of(
                PasswordValidationResponse(
                    true,
                    ""
                ), "A23456789"),
            Arguments.of(
                PasswordValidationResponse(
                    false,
                    "Password must be at least 8 characters"
                ), "C234567"),
            Arguments.of(
                PasswordValidationResponse(
                    false,
                    "Password must be at least 8 characters"
                ), ""),
        )
    }
}