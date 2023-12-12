package domain.katas.kata3.validator

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.katas.domain.katas.kata3.dto.PasswordValidationResult
import org.katas.domain.katas.kata3.validator.NumberToEnglishConvertor
import org.katas.domain.katas.kata3.validator.SpecialValidator

class SpecialValidatorTest {
    private val mockNumberToEnglishConvertor = mockk<NumberToEnglishConvertor> {
        every { convert(any()) } answers { "one" }
    }
    private val specialValidator = SpecialValidator(numberToEnglishConvertor = mockNumberToEnglishConvertor)

    @DisplayName("validate should return expected result based on special character")
    @ParameterizedTest
    @MethodSource("validateData")
    fun testValidate(expectedValue: PasswordValidationResult, input: String){
        val result = specialValidator.validate(input)

        Assertions.assertEquals(expectedValue, result)
    }

    companion object {
        @JvmStatic
        fun validateData() = listOf(
            Arguments.of(
                PasswordValidationResult(
                    true,
                    mutableSetOf()
                ), "Bcdedit@"),
            Arguments.of(
                PasswordValidationResult(
                    false,
                    mutableSetOf("Password must contain at least one special character")
                ), "bcdedit"),
            Arguments.of(
                PasswordValidationResult(
                    false,
                    mutableSetOf("Password must contain at least one special character")
                ), ""),
        )
    }
}
