package org.katas.domain.katas.kata3.validator

import org.katas.domain.katas.kata3.dto.PasswordValidationResponse

class CapitalValidator(private val minCapitalCharacter: Int = 1,
                       private val numberToEnglishConvertor: NumberToEnglishConvertor): Validator {

    private var errorMessageFormat = "Password must contain at least %s capital letter"

    override fun validate(password: String): PasswordValidationResponse {
        if(this.numberOfCapitalCharacters(password) < minCapitalCharacter) {
            val englishNumber = numberToEnglishConvertor.convert(minCapitalCharacter)
            return PasswordValidationResponse(false, errorMessageFormat.format(englishNumber))
        }
        return PasswordValidationResponse(true, null)
    }

    private fun numberOfCapitalCharacters(string: String): Int {
        var ans = 0
        for(char in string){
            if(char.isUpperCase())
                ans++
        }

        return ans
    }
}