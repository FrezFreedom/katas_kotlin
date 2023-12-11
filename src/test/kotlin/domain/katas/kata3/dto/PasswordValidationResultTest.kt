package domain.katas.kata3.dto

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.katas.domain.katas.kata3.dto.PasswordValidationResult

class PasswordValidationResultTest {

    @ParameterizedTest
    @MethodSource("updateData")
    fun testUpdate(expectedValue: PasswordValidationResult,
                   baseResponse: PasswordValidationResult,
                   extraResponse: PasswordValidationResult){

        baseResponse.update(extraResponse)

        Assertions.assertEquals(expectedValue, baseResponse)
    }

    companion object {
        @JvmStatic
        fun updateData() = listOf(
            Arguments.of(
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(true, mutableSetOf()),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("a")),
                PasswordValidationResult(false, mutableSetOf("a")),
                PasswordValidationResult(true, mutableSetOf()),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("a")),
                PasswordValidationResult(true, mutableSetOf()),
                PasswordValidationResult(false, mutableSetOf("a")),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("a", "b")),
                PasswordValidationResult(false, mutableSetOf("a")),
                PasswordValidationResult(false, mutableSetOf("b")),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("a", "b", "c")),
                PasswordValidationResult(false, mutableSetOf("b", "c")),
                PasswordValidationResult(false, mutableSetOf("a")),
            ),
            Arguments.of(
                PasswordValidationResult(false, mutableSetOf("a", "b", "c")),
                PasswordValidationResult(false, mutableSetOf("b", "c")),
                PasswordValidationResult(false, mutableSetOf("a", "c")),
            ),
        )
    }

}