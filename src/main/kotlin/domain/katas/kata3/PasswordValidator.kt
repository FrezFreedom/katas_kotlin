package org.katas.domain.katas.kata3

import org.katas.domain.katas.kata3.dto.PasswordValidationResponse
import org.katas.domain.katas.kata3.validator.CapitalValidator
import org.katas.domain.katas.kata3.validator.LengthValidator
import org.katas.domain.katas.kata3.validator.NumericValidator
import org.katas.domain.katas.kata3.validator.SpecialValidator

class PasswordValidator(private val lengthValidator: LengthValidator,
                        private val numericValidator: NumericValidator,
                        private val capitalValidator: CapitalValidator,
                        private val specialValidator: SpecialValidator,) {
    fun validate(password: String): PasswordValidationResponse {
        val validationResult = PasswordValidationResponse(true, null)

        validationResult.update( this.validateLength(password) )
        validationResult.update( this.validateNumeric(password) )
        validationResult.update( this.validateCapital(password) )
        validationResult.update( this.validateSpecial(password) )

        return validationResult
    }

    private fun validateLength(password: String): PasswordValidationResponse {
        return lengthValidator.validate(password)
    }

    private fun validateNumeric(password: String): PasswordValidationResponse {
        return numericValidator.validate(password)
    }

    private fun validateCapital(password: String): PasswordValidationResponse {
        return capitalValidator.validate(password)
    }

    private fun validateSpecial(password: String): PasswordValidationResponse {
        return specialValidator.validate(password)
    }
}