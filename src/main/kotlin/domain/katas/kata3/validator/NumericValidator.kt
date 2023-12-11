package org.katas.domain.katas.kata3.validator

import org.katas.domain.katas.kata3.dto.PasswordValidationResult

class NumericValidator(private val minNumericCharacter: Int = 2): Validator {
    private var errorMessageFormat = "The password must contain at least %d numbers"

    override fun validate(password: String): PasswordValidationResult {
        if(this.numberOfNumericCharacters(password) < minNumericCharacter)
            return PasswordValidationResult(false, mutableSetOf(errorMessageFormat.format(minNumericCharacter)))

        return PasswordValidationResult(true, mutableSetOf())
    }

    private fun numberOfNumericCharacters(string: String): Int {
        var ans = 0
        for(char in string){
            if(char.isDigit())
                ans++
        }

        return ans
    }
}