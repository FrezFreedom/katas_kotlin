package org.katas.domains.katas.kata3

class PasswordValidator {
    fun validate(password: String): PasswordValidationResponse {
        val lengthValidationResult = this.validateLength(password)
        if(!lengthValidationResult.isValid)
            return lengthValidationResult

        val numericValidationResult = this.validateNumeric(password)
        if(!numericValidationResult.isValid)
            return this.validateNumeric(password)

        return PasswordValidationResponse(true, "")
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