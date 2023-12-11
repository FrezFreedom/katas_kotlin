package domain.katas.kata3

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.katas.domain.katas.kata3.dto.PasswordValidationResponse
import org.katas.domain.katas.kata3.PasswordValidator
import org.katas.domain.katas.kata3.validator.CapitalValidator
import org.katas.domain.katas.kata3.validator.LengthValidator
import org.katas.domain.katas.kata3.validator.NumericValidator

class PasswordValidatorTest {
    private val lengthValidator = mockk<LengthValidator>()
    private val numericValidator = mockk<NumericValidator>()
    private val capitalValidator = mockk<CapitalValidator>()
    private lateinit var passwordValidator: PasswordValidator

    @ParameterizedTest
    @MethodSource("validateData")
    fun testValidate1(expectedResponse: PasswordValidationResponse,
                      lengthValidatorMockResponse: PasswordValidationResponse,
                      numericValidatorMockResponse: PasswordValidationResponse,
                      capitalValidatorMockResponse: PasswordValidationResponse,) {

        every { lengthValidator.validate(any()) } returns lengthValidatorMockResponse
        every { numericValidator.validate(any()) } returns numericValidatorMockResponse
        every { capitalValidator.validate(any()) } returns capitalValidatorMockResponse

        passwordValidator = PasswordValidator(lengthValidator, numericValidator, capitalValidator)

        val validationResult = passwordValidator.validate("nothing")

        Assertions.assertEquals(expectedResponse, validationResult)
    }

    companion object {
        @JvmStatic
        fun validateData() = listOf(
            Arguments.of(
                PasswordValidationResponse(true, null),
                PasswordValidationResponse(true, null),
                PasswordValidationResponse(true, null),
                PasswordValidationResponse(true, null),
            ),
            Arguments.of(
                PasswordValidationResponse(false, "x"),
                PasswordValidationResponse(false, "x"),
                PasswordValidationResponse(true, null),
                PasswordValidationResponse(true, null),
            ),
            Arguments.of(
                PasswordValidationResponse(false, "y"),
                PasswordValidationResponse(true, null),
                PasswordValidationResponse(false, "y"),
                PasswordValidationResponse(true, null),
            ),
            Arguments.of(
                PasswordValidationResponse(false, "z"),
                PasswordValidationResponse(true, null),
                PasswordValidationResponse(true, null),
                PasswordValidationResponse(false, "z"),
            ),
            Arguments.of(
                PasswordValidationResponse(false, "x\\ny"),
                PasswordValidationResponse(false, "x"),
                PasswordValidationResponse(false, "y"),
                PasswordValidationResponse(true, null),
            ),
            Arguments.of(
                PasswordValidationResponse(false, "y\\nz"),
                PasswordValidationResponse(true, null),
                PasswordValidationResponse(false, "y"),
                PasswordValidationResponse(false, "z"),
            ),
            Arguments.of(
                PasswordValidationResponse(false, "x\\nz"),
                PasswordValidationResponse(false, "x"),
                PasswordValidationResponse(true, null),
                PasswordValidationResponse(false, "z"),
            ),
            Arguments.of(
                PasswordValidationResponse(false, "x\\ny\\nz"),
                PasswordValidationResponse(false, "x"),
                PasswordValidationResponse(false, "y"),
                PasswordValidationResponse(false, "z"),
            ),
            Arguments.of(
                PasswordValidationResponse(false, "x\\nxx\\ny\\nz"),
                PasswordValidationResponse(false, "x\\nxx"),
                PasswordValidationResponse(false, "y"),
                PasswordValidationResponse(false, "z"),
            ),
            Arguments.of(
                PasswordValidationResponse(false, "x\\ny\\nyy\\nz"),
                PasswordValidationResponse(false, "x"),
                PasswordValidationResponse(false, "y\\nyy"),
                PasswordValidationResponse(false, "z"),
            ),
            Arguments.of(
                PasswordValidationResponse(false, "x\\ny\\nz\\nzz"),
                PasswordValidationResponse(false, "x"),
                PasswordValidationResponse(false, "y"),
                PasswordValidationResponse(false, "z\\nzz"),
            ),
            Arguments.of(
                PasswordValidationResponse(false, "x\\nxx\\ny\\nyy\\nz\\nzz"),
                PasswordValidationResponse(false, "x\\nxx"),
                PasswordValidationResponse(false, "y\\nyy"),
                PasswordValidationResponse(false, "z\\nzz"),
            ),
        )
    }

}