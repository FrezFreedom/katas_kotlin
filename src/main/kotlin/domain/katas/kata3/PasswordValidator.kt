package org.katas.domain.katas.kata3

import org.katas.domain.katas.kata3.dto.PasswordValidationResult
import org.katas.domain.katas.kata3.validator.*

class PasswordValidator(private val lengthValidator: LengthValidator,
                        private val numericValidator: NumericValidator,
                        private val capitalValidator: CapitalValidator,
                        private val specialValidator: SpecialValidator,): Validator {
    override fun validate(password: String): PasswordValidationResult {
        val validationResult = PasswordValidationResult(true, mutableSetOf())

        val listOfValidators = listOf(lengthValidator, numericValidator, capitalValidator, specialValidator)
        for(validator in listOfValidators){
            validationResult.update( validator.validate(password) )
        }

        return validationResult
    }
}