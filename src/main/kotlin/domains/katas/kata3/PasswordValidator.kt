package org.katas.domains.katas.kata3

class PasswordValidator {
    fun validate(password: String): PasswordValidationResponse {
        val lengthValidationResult = this.validateLength(password)
        if(!lengthValidationResult.isValid)
            return lengthValidationResult

        return this.validateNumeric(password)
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