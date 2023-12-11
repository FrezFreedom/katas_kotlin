package domain.katas.kata3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.katas.domain.katas.kata3.dto.PasswordValidationResponse
import org.katas.domain.katas.kata3.PasswordValidator

class PasswordValidatorTest {

    private val passwordValidator = PasswordValidator()

    @ParameterizedTest
    @MethodSource("passwordValidatorData")
    fun testPasswordValidator(expectedValue: PasswordValidationResponse, input: String){

        val result = passwordValidator.validate(input)

        Assertions.assertEquals(expectedValue, result)
    }

    companion object {
        @JvmStatic
        fun passwordValidatorData() = listOf(
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
                "The password must contain at least 2 numbers"
            ), "CefGlue7"),
            Arguments.of(
                PasswordValidationResponse(
                true,
                ""
            ), "CefGlu17"),
            Arguments.of(
                PasswordValidationResponse(
                    false,
                    "Password must be at least 8 characters\\nThe password must contain at least 2 numbers"
            ), "CefGlu1"),
            Arguments.of(
                PasswordValidationResponse(
                    false,
                    "Password must contain at least one capital letter"
            ), "wooden12"),
        )
    }

}