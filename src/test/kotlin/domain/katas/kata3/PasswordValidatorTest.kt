package domain.katas.kata3

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.katas.domain.katas.kata3.dto.PasswordValidationResult
import org.katas.domain.katas.kata3.PasswordValidator
import org.katas.domain.katas.kata3.validator.CapitalValidator
import org.katas.domain.katas.kata3.validator.LengthValidator
import org.katas.domain.katas.kata3.validator.NumericValidator
import org.katas.domain.katas.kata3.validator.SpecialValidator

class PasswordValidatorTest {
    private val lengthValidator = mockk<LengthValidator>()
    private val numericValidator = mockk<NumericValidator>()
    private val capitalValidator = mockk<CapitalValidator>()
    private val specialValidator = mockk<SpecialValidator>()
    private lateinit var passwordValidator: PasswordValidator

    @ParameterizedTest
    @MethodSource("validateData")
    fun testValidate1(expectedResponse: PasswordValidationResult,
                      lengthValidatorMockResponse: PasswordValidationResult,
                      numericValidatorMockResponse: PasswordValidationResult,
                      capitalValidatorMockResponse: PasswordValidationResult,
                      specialValidatorMockResponse: PasswordValidationResult,) {

        every { lengthValidator.validate(any()) } returns lengthValidatorMockResponse
        every { numericValidator.validate(any()) } returns numericValidatorMockResponse
        every { capitalValidator.validate(any()) } returns capitalValidatorMockResponse
        every { specialValidator.validate(any()) } returns  specialValidatorMockResponse

        passwordValidator = PasswordValidator(lengthValidator, numericValidator, capitalValidator, specialValidator)

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

}