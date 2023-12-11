package org.katas.domain.katas.kata3.validator

import org.katas.domain.katas.kata3.dto.PasswordValidationResult

class LengthValidator(private val minPasswordSize: Int = 8): Validator {
    private var errorMessageFormat = "Password must be at least %s characters"

    override fun validate(password: String): PasswordValidationResult {
        if(password.length < minPasswordSize) return PasswordValidationResult(false, mutableSetOf(errorMessageFormat.format(minPasswordSize)))
        return PasswordValidationResult(true, mutableSetOf())
    }
}