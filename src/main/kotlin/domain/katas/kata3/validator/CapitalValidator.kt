package org.katas.domain.katas.kata3.validator

import org.katas.domain.katas.kata3.dtos.PasswordValidationResponse

class CapitalValidator(private val minCapitalCharacter: Int = 1,
                       private val numberToEnglishConvertor: NumberToEnglishConvertor) {

    private var errorMessageFormat = "Password must contain at least %s capital letter"

    fun validate(password: String): PasswordValidationResponse {
        if(this.numberOfCapitalCharacters(password) < minCapitalCharacter) {
            val englishNumber = numberToEnglishConvertor.convert(minCapitalCharacter)
            return PasswordValidationResponse(false, errorMessageFormat.format(englishNumber))
        }
        return PasswordValidationResponse(true, "")
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