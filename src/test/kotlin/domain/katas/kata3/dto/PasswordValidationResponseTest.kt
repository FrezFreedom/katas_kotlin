package domain.katas.kata3.dto

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.katas.domain.katas.kata3.dto.PasswordValidationResponse

class PasswordValidationResponseTest {

    @ParameterizedTest
    @MethodSource("updateData")
    fun testUpdate(expectedValue: PasswordValidationResponse,
                   baseResponse: PasswordValidationResponse,
                   extraResponse: PasswordValidationResponse){

        baseResponse.update(extraResponse)

        Assertions.assertEquals(expectedValue, baseResponse)
    }

    companion object {
        @JvmStatic
        fun updateData() = listOf(
            Arguments.of(
                PasswordValidationResponse(true, null),
                PasswordValidationResponse(true, null),
                PasswordValidationResponse(true, null),
            ),
            Arguments.of(
                PasswordValidationResponse(false, "a"),
                PasswordValidationResponse(false, "a"),
                PasswordValidationResponse(true, null),
            ),
            Arguments.of(
                PasswordValidationResponse(false, "a"),
                PasswordValidationResponse(true, null),
                PasswordValidationResponse(false, "a"),
            ),
            Arguments.of(
                PasswordValidationResponse(false, "a\\nb"),
                PasswordValidationResponse(false, "a"),
                PasswordValidationResponse(false, "b"),
            ),
            Arguments.of(
                PasswordValidationResponse(false, "b\\na"),
                PasswordValidationResponse(false, "b"),
                PasswordValidationResponse(false, "a"),
            ),
            Arguments.of(
                PasswordValidationResponse(false, "b\\nc\\na"),
                PasswordValidationResponse(false, "b\\nc"),
                PasswordValidationResponse(false, "a"),
            ),
            Arguments.of(
                PasswordValidationResponse(false, "b\\na\\nc"),
                PasswordValidationResponse(false, "b"),
                PasswordValidationResponse(false, "a\\nc"),
            ),
        )
    }

}