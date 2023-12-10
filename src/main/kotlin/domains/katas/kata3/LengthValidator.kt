package org.katas.domains.katas.kata3

class LengthValidator(private val minPasswordSize: Int = 8) {
    private var errorMessageFormat = "Password must be at least %s characters";

    fun validate(password: String): PasswordValidationResponse {
        if(password.length < minPasswordSize) return PasswordValidationResponse(false, errorMessageFormat.format(minPasswordSize))
        return PasswordValidationResponse(true, "")
    }
}