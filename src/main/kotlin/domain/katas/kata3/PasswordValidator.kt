package org.katas.domain.katas.kata3

import org.katas.domain.katas.kata3.dto.PasswordValidationResponse
import org.katas.domain.katas.kata3.validator.CapitalValidator
import org.katas.domain.katas.kata3.validator.LengthValidator
import org.katas.domain.katas.kata3.validator.NumberToEnglishConvertor
import org.katas.domain.katas.kata3.validator.NumericValidator

class PasswordValidator {
    fun validate(password: String): PasswordValidationResponse {
        val validationResult = PasswordValidationResponse(true, null)

        validationResult.update( this.validateLength(password) )
        validationResult.update( this.validateNumeric(password) )
        validationResult.update( this.validateCapital(password) )

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

    private fun validateCapital(password: String): PasswordValidationResponse {
        val numberToEnglishConvertor = NumberToEnglishConvertor()
        val capitalValidator = CapitalValidator(numberToEnglishConvertor = numberToEnglishConvertor)
        return capitalValidator.validate(password)
    }
}