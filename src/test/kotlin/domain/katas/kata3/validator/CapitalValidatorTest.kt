package domain.katas.kata3.validator

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.katas.domain.katas.kata3.dtos.PasswordValidationResponse
import org.katas.domain.katas.kata3.validator.CapitalValidator
import org.katas.domain.katas.kata3.validator.NumberToEnglishConvertor
import kotlin.test.BeforeTest


class CapitalValidatorTest {

    private lateinit var capitalValidator: CapitalValidator
    private lateinit var mockNumberToEnglishConvertor: NumberToEnglishConvertor

    @BeforeTest
    fun setUp() {
        mockNumberToEnglishConvertor = mockk<NumberToEnglishConvertor>()
        every { mockNumberToEnglishConvertor.convert(any()) } answers { "one" }

        capitalValidator = CapitalValidator(numberToEnglishConvertor = mockNumberToEnglishConvertor)
    }

    @ParameterizedTest
    @MethodSource("validateData")
    fun testValidate(expectedValue: PasswordValidationResponse, input: String){

        val result = capitalValidator.validate(input)

        Assertions.assertEquals(expectedValue, result)
    }

    companion object {
        @JvmStatic
        fun validateData() = listOf(
            Arguments.of(
                PasswordValidationResponse(
                    true,
                    ""
                ), "Bcdedit"),
            Arguments.of(
                PasswordValidationResponse(
                    false,
                    "Password must contain at least one capital letter"
                ), "bcdedit"),
            Arguments.of(
                PasswordValidationResponse(
                    false,
                    "Password must contain at least one capital letter"
                ), ""),
        )
    }


}
