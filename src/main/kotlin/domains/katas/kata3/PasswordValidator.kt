package org.katas.domains.katas.kata3

class PasswordValidator {
    fun validate(password: String): PasswordValidationResponse  {

        return when {
            password.length < 8 -> PasswordValidationResponse(false, "Password must be at least 8 characters")
            else -> PasswordValidationResponse(true, "")
        }
    }
}