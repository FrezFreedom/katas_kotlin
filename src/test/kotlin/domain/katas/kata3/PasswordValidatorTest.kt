package domain.katas.kata3

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.katas.domain.katas.kata3.dto.PasswordValidationResult
import org.katas.domain.katas.kata3.PasswordValidator
import org.katas.domain.katas.kata3.validator.*

class PasswordValidatorTest {
    private val lengthValidator = mockk<Validator>()
    private val numericValidator = mockk<Validator>()
    private val capitalValidator = mockk<Validator>()
    private val specialValidator = mockk<Validator>()
    private lateinit var passwordValidator: PasswordValidator

    @DisplayName("validate should return expected result based on individual validators")
    @ParameterizedTest
    @MethodSource("validateData")
    fun testValidate(expectedResponse: PasswordValidationResult,
                      lengthValidatorMockResponse: PasswordValidationResult,
                      numericValidatorMockResponse: PasswordValidationResult,
                      capitalValidatorMockResponse: PasswordValidationResult,
                      specialValidatorMockResponse: PasswordValidationResult,) {

        every { lengthValidator.validate(any()) } returns lengthValidatorMockResponse
        every { numericValidator.validate(any()) } returns numericValidatorMockResponse
        every { capitalValidator.validate(any()) } returns capitalValidatorMockResponse
        every { specialValidator.validate(any()) } returns  specialValidatorMockResponse

        passwordValidator = PasswordValidator(listOf( lengthValidator, numericValidator, capitalValidator, specialValidator ))

        val validationResult = passwordValidator.validate("nothing")

        Assertions.assertEquals(expectedResponse, validationResult)
    }

    companion object {
        @JvmStatic
        fun validateData() = listOf(
            Arguments.of(
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(true, mutableSetOf()),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("x")),
                PasswordValidationResult(false, mutableSetOf("x")),
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(true, mutableSetOf()),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("y")),
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(false, mutableSetOf("y")),
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(true, mutableSetOf()),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("z")),
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(false, mutableSetOf("z")),
                PasswordValidationResult(true, mutableSetOf()),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("x", "y")),
                PasswordValidationResult(false, mutableSetOf("x")),
                PasswordValidationResult(false, mutableSetOf("y")),
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(true, mutableSetOf()),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("y", "z")),
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(false, mutableSetOf("y")),
                PasswordValidationResult(false, mutableSetOf("z")),
                PasswordValidationResult(true, mutableSetOf()),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("x", "z")),
                PasswordValidationResult(false, mutableSetOf("x")),
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(false, mutableSetOf("z")),
                PasswordValidationResult(true, mutableSetOf()),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("x", "y", "z")),
                PasswordValidationResult(false, mutableSetOf("x")),
                PasswordValidationResult(false, mutableSetOf("y")),
                PasswordValidationResult(false, mutableSetOf("z")),
                PasswordValidationResult(true, mutableSetOf()),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("x", "xx", "y", "z")),
                PasswordValidationResult(false, mutableSetOf("x", "xx")),
                PasswordValidationResult(false, mutableSetOf("y")),
                PasswordValidationResult(false, mutableSetOf("z")),
                PasswordValidationResult(true, mutableSetOf()),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("x","y","yy","z")),
                PasswordValidationResult(false, mutableSetOf("x")),
                PasswordValidationResult(false, mutableSetOf("y", "yy")),
                PasswordValidationResult(false, mutableSetOf("z")),
                PasswordValidationResult(true, mutableSetOf()),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("x", "y", "z", "zz")),
                PasswordValidationResult(false, mutableSetOf("x")),
                PasswordValidationResult(false, mutableSetOf("y")),
                PasswordValidationResult(false, mutableSetOf("z", "zz")),
                PasswordValidationResult(true, mutableSetOf()),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("x", "xx", "y", "yy", "z", "zz")),
                PasswordValidationResult(false, mutableSetOf("x", "xx")),
                PasswordValidationResult(false, mutableSetOf("y", "yy")),
                PasswordValidationResult(false, mutableSetOf("z", "zz")),
                PasswordValidationResult(true, mutableSetOf()),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("g")),
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(false, mutableSetOf("g")),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("x", "xx", "y", "yy", "z", "zz", "g", "gg")),
                PasswordValidationResult(false, mutableSetOf("x", "xx")),
                PasswordValidationResult(false, mutableSetOf("y", "yy")),
                PasswordValidationResult(false, mutableSetOf("z", "zz")),
                PasswordValidationResult(false, mutableSetOf("g", "gg")),
            ),
        )
    }


    @Test
    fun testPasswordValidatorEndToEnd() {
        val listOfValidators = listOf(LengthValidator(), CapitalValidator(numberToEnglishConvertor = NumberToEnglishConvertor()))
        val passwordValidator = PasswordValidator(listOfValidators)

        val result = passwordValidator.validate("abcd")

        Assertions.assertEquals(false, result.isValid)
        Assertions.assertEquals(setOf("Password must be at least 8 characters", "Password must contain at least one capital letter"), result.error)

    }

}