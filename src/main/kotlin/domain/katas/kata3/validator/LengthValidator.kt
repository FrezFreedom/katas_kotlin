package org.katas.domain.katas.kata3.validator

import com.google.inject.Inject
import com.google.inject.Singleton
import org.katas.domain.katas.kata3.dto.PasswordValidationResult

@Singleton
class LengthValidator @Inject constructor(private val config: ValidatorConfig): Validator {
    private var errorMessageFormat = "Password must be at least %s characters"

    override fun validate(password: String): PasswordValidationResult {
        if(password.length < config.minPasswordSize) return PasswordValidationResult(false, mutableSetOf(errorMessageFormat.format(config.minPasswordSize)))
        return PasswordValidationResult(true, mutableSetOf())
    }
}