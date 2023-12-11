package org.katas.domain.katas.kata3.validator

import org.katas.domain.katas.kata3.dtos.PasswordValidationResponse

class NumericValidator(private val minNumericCharacter: Int = 2) {
    private var errorMessageFormat = "The password must contain at least %d numbers"

    fun validate(password: String): PasswordValidationResponse {
        if(this.numberOfNumericCharacters(password) < minNumericCharacter)
            return PasswordValidationResponse(false, errorMessageFormat.format(minNumericCharacter))

        return PasswordValidationResponse(true, "")
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