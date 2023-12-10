package org.katas.domains.katas.kata3

import org.katas.domains.katas.kata3.dtos.PasswordValidationResponse
import org.katas.domains.katas.kata3.validators.LengthValidator
import org.katas.domains.katas.kata3.validators.NumericValidator

class PasswordValidator {
    fun validate(password: String): PasswordValidationResponse {
        val validationResult = PasswordValidationResponse(true, "")

        validationResult.update( this.validateLength(password) )
        validationResult.update( this.validateNumeric(password) )

        return validationResult
    }

    private fun validateLength(password: String): PasswordValidationResponse {
        val lengthValidator = LengthValidator()
        return lengthValidator.validate(password)
    }

    private fun validateNumeric(password: String): PasswordValidationResponse {
        val numericValidator = NumericValidator()
        return numericValidator.validate(password)
    }
}