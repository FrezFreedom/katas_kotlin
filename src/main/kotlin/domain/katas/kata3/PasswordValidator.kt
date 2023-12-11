package org.katas.domain.katas.kata3

import org.katas.domain.katas.kata3.dto.PasswordValidationResult
import org.katas.domain.katas.kata3.validator.*

class PasswordValidator(private val validators: List<Validator>): Validator {
    override fun validate(password: String): PasswordValidationResult {
        val validationResult = PasswordValidationResult(true, mutableSetOf())

        for(validator in validators){
            validationResult.update( validator.validate(password) )
        }

        return validationResult
    }
}