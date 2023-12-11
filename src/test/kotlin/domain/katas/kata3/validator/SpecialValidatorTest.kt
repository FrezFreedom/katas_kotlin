package domain.katas.kata3.validator

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.katas.domain.katas.kata3.dto.PasswordValidationResponse
import org.katas.domain.katas.kata3.validator.NumberToEnglishConvertor
import org.katas.domain.katas.kata3.validator.SpecialValidator

class SpecialValidatorTest {
    private val mockNumberToEnglishConvertor = mockk<NumberToEnglishConvertor> {
        every { convert(any()) } answers { "one" }
    }
    private val specialValidator = SpecialValidator(numberToEnglishConvertor = mockNumberToEnglishConvertor)

    @ParameterizedTest
    @MethodSource("validateData")
    fun testValidate(expectedValue: PasswordValidationResponse, input: String){
        val result = specialValidator.validate(input)

        Assertions.assertEquals(expectedValue, result)
    }

    companion object {
        @JvmStatic
        fun validateData() = listOf(
            Arguments.of(
                PasswordValidationResponse(
                    true,
                    null
                ), "Bcdedit@"),
            Arguments.of(
                PasswordValidationResponse(
                    false,
                    "Password must contain at least one special character"
                ), "bcdedit"),
            Arguments.of(
                PasswordValidationResponse(
                    false,
                    "Password must contain at least one special character"
                ), ""),
        )
    }
}
