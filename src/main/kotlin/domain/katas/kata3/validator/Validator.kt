package org.katas.domain.katas.kata3.validator

import org.katas.domain.katas.kata3.dto.PasswordValidationResponse

interface Validator {
    fun validate(password: String): PasswordValidationResponse
}