package domains.katas.kata3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.katas.domains.katas.kata3.dtos.PasswordValidationResponse
import org.katas.domains.katas.kata3.PasswordValidator

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
            ), "123456789"),
            Arguments.of(
                PasswordValidationResponse(
                false,
                "Password must be at least 8 characters"
            ), "1234567"),
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
        )
    }

}