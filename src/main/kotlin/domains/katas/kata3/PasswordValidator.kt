package org.katas.domains.katas.kata3

class PasswordValidator {
    fun validate(password: String): PasswordValidationResponse {
        return this.validateLength(password)
    }

    private fun validateLength(password: String): PasswordValidationResponse {
        val lengthValidator = LengthValidator()
        return lengthValidator.validate(password)
    }
}